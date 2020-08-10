package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.ApplicationConfig;
import com.example.demo.exception.AppBizException;
import com.example.demo.util.FileUtil;
import com.example.demo.util.MultipartFileToFile;
import com.example.demo.util.Sm3Utils;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * 功能：
 *
 * @author 2020/1/14
 * @author zoulinjun
 */
@Slf4j
@RestController
public class FaceParmController {

    @Resource
    private ApplicationConfig applicationConfig;

    @ResponseBody
    @RequestMapping(value = "/face/getSignAndCiper.htm", method = RequestMethod.POST)
    public JSONObject hello(HttpServletRequest request) throws Throwable{
        log.info("------------------接收参数------------------------");
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("pic");

        Map<String,String[]> map = params.getParameterMap();
        JSONObject json = map2Json(map);
        log.info("请求参数："+ json.toJSONString());

        if(files == null ||files.size() == 0){
            throw new AppBizException("0001","未上传图片");
        }
        //图片加密
        MultipartFile file = files.get(0);
        File f =  MultipartFileToFile.multipartFileToFile(file);
        String picStr = FileUtil.file2Base64(f);
        //测试base64串是否正确
//        FileUtil.base64ToFile(picStr);
        log.info("pic原:"+picStr);
        log.info("pic原:"+Base64.toBase64String(file.getBytes()));//两种方式获取
        String picMi = encodePic(picStr,json.getString("enc_key"));
        log.info("pic密:" + picMi);

        //pin加密
//        String pinMi = encodePin(json.getString("pin"),json.getString("xor_salt"),json.getString("enc_key"));
        String pinMi = handlePin(json.getString("enc_key"),json.getString("pin"),json.getString("xor_salt"));
        log.info("pin密:" + pinMi);

        //对称密钥加密
        String encKeyMi = encodeEncKey(applicationConfig.getEnc_key_pub(),json.getString("enc_key"));
        log.info("encKey密:" + encKeyMi);

        //加签名
        JSONObject res = new JSONObject();
        res.put("version",json.getString("version"));
        res.put("accessId",json.getString("accessId"));
        res.put("merNo",json.getString("merNo"));
        res.put("terNo",json.getString("terNo"));
        res.put("batchNo",json.getString("batchNo"));
        res.put("orderNo",json.getString("orderNo"));
        res.put("orderTime",json.get("orderTime"));
        res.put("orderType",json.getString("orderType"));
        JSONObject trans_data = new JSONObject();
        res.put("trans_data",trans_data);
        trans_data.put("rout_md",json.get("rout_md"));
        trans_data.put("phone_no",json.getString("phone_no"));
        trans_data.put("pic",picMi);
        trans_data.put("pin",pinMi);
        trans_data.put("xor_salt",json.getString("xor_salt"));
        trans_data.put("bio_rslt",json.getString("bio_rslt"));
        trans_data.put("trx_amt",json.get("trx_amt"));
        trans_data.put("trx_curr",json.getString("trx_curr"));
        trans_data.put("trx_loc",json.getString("trx_loc"));
        res.put("enc_key",encKeyMi);
        res.put("req_resv",json.getString("req_resv"));

        String sign = getSign(res,applicationConfig.getSign_private_key());
        res.put("signature",sign);
        return res;
    }

        private JSONObject map2Json(Map<String,String[]> reqMap){
            Map<String, Object> tempMap = new HashMap<>();
            Set<Map.Entry<String, String[]>> set = reqMap.entrySet();
            Iterator<Map.Entry<String, String[]>> it = set.iterator();
            while (it.hasNext()) {
                Map.Entry<String, String[]> entry = it.next();

                System.out.println("KEY:"+entry.getKey());
                for (String str : entry.getValue()) {
                    System.out.println(str);
                    tempMap.put(entry.getKey(), str);
                }
            }
            return new JSONObject(tempMap);
        }

        private String encodePic(String picStr,String enc_key_pic){
            String encode = null;

            // base64解码(获取图片二进制原字节流)
            byte picDa [] = org.bouncycastle.util.encoders.Base64.decode(picStr);

            // 获取人脸对称密钥字节流
            byte key [] = hexStringToByte(enc_key_pic);
//		byte key [] = enc_key_pic.getBytes();

            // 使用SM4加密 （padding 方式PAD_PKCS7）
            byte[] result = sm4CbcEncrypt(key, picDa, "PKCS7Padding");

            // base64编码
            encode = org.bouncycastle.util.encoders.Base64.toBase64String(result);

            return encode;
        }
        public static SecretKeySpec getSm4Key(byte[] key) {
            if (key.length != 16) {
                System.out.println("SM4's key should be 16bytes, 128bits");
            }
            return new SecretKeySpec(key, "SM4");
        }

        public static IvParameterSpec getIv(int len) {
            //使用 IV 的例子是反馈模式中的密码，如，CBC 模式中的 DES 和使用 OAEP 编码操作的 RSA 密码
            byte[] zero = new byte[len];
            IvParameterSpec ivps = new IvParameterSpec(zero);
            return ivps;
        }
        private byte[] sm4CbcEncrypt(byte[] key, byte[] data, String padMode){
            byte[] res = null;
            String algorithm = "SM4/CBC/" + padMode;
            try {
                Security.addProvider(new BouncyCastleProvider());
                Cipher cipher = Cipher.getInstance(algorithm,"BC");
                SecretKeySpec secretKeySpec = getSm4Key(key);
                IvParameterSpec ivParameterSpec = getIv(cipher.getBlockSize());
                //byte[] padData = padding(data, cipher.getBlockSize());改为pkcs7填充 由bc来完成
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
                res = cipher.doFinal(data);
                return res;
            } catch (Exception e) {
                log.error("Fail: Sm4 Cbc Encrypt",e);
                throw new AppBizException("9998","Sm4加密异常,请联系开发人员");
            }
        }

        private byte[] hexStringToByte(String hex){
            int len = (hex.length() / 2);
            byte[] result = new byte[len];
            char[] achar = hex.toCharArray();
            for (int i = 0; i < len; i++) {
                int pos = i * 2;
                result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
            }
            return result;
        }

    private  byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    private String encodePin(String pin,String solt,String enc_key_pic){
        String encode = null;

        Long pinLong = Long.parseLong(pin);
        Long soltLong = Long.parseLong(solt);
        Long lon = pinLong^soltLong;
        log.info("异或后:"+lon);
        byte picDa [] = lon.toString().getBytes();

        // 获取人脸对称密钥字节流
        byte key [] = hexStringToByte(enc_key_pic);
//		byte key [] = enc_key_pic.getBytes();

        // 使用SM4加密 （padding 方式PAD_PKCS7）
        byte[] result = sm4CbcEncrypt(key, picDa, "PKCS7Padding");

        // base64编码
        encode = Base64.toBase64String(result);

        return encode;
        }

    private  String handlePin(String enc_key_sen, String pin, String xor_salt)
    {
        String encode = null;

        // 获取敏感数据密钥字节流
        byte key [] = hexStringToByte(enc_key_sen);

        //按照卡号要求进行截取
        String Strpanblock = xor_salt.substring(xor_salt.length()-1-12, xor_salt.length()-1);

        byte[] panBlock = new byte[16];
        System.arraycopy(hexStringToByte(Strpanblock), 0, panBlock, 10, 6);

        String Spinblock = "06" + pin + "FFFFFFFFFFFFFFFFFFFFFFFF";

        byte[] pinBlock = hexStringToByte(Spinblock);

        // 异或
        byte[] xorRlt = new byte[16];
        for (int i = 0; i < xorRlt.length; i++) {
            xorRlt[i] = (byte) (panBlock[i] ^ pinBlock[i]);
        }

        //使用SM4加密(padding 方式PAD_NO)
        byte[] result = sm4CbcEncrypt(key, xorRlt, "NoPadding");

        //base64编码
        encode = Base64.toBase64String(result);

        return encode;
    }

        private String encodeEncKey(String key, String data){
            PublicKey publicKey = getPublicKey(key);
            ECPublicKeyParameters ecPublicKeyParameters = null;
            if (publicKey instanceof BCECPublicKey) {
                BCECPublicKey bcecPublicKey = (BCECPublicKey) publicKey;
                ECParameterSpec ecParameterSpec = bcecPublicKey.getParameters();
                ECDomainParameters ecDomainParameters = new ECDomainParameters(ecParameterSpec.getCurve(),
                        ecParameterSpec.getG(), ecParameterSpec.getN());
                ecPublicKeyParameters = new ECPublicKeyParameters(bcecPublicKey.getQ(), ecDomainParameters);
            }

            SM2Engine sm2Engine = new SM2Engine();
            sm2Engine.init(true, new ParametersWithRandom(ecPublicKeyParameters, new SecureRandom()));

            byte[] arrayOfBytes = null;
            try {
                byte[] in = data.getBytes("utf-8");
                arrayOfBytes = sm2Engine.processBlock(in,0, in.length);
            } catch (Exception e) {
                log.error("SM2加密时出现异常:", e);
                throw new AppBizException("9998","Sm2加密异常,请联系开发人员");
            }
            return Base64.toBase64String(arrayOfBytes);
        }

    public static PublicKey getPublicKey(String pubKey) {
        try {
            log.info("通过公钥串获取公钥证书对象开始");
            Security.addProvider(new BouncyCastleProvider());
            byte[] publicBytes = Base64.decode(pubKey);
            X509EncodedKeySpec eks = new X509EncodedKeySpec(publicBytes);
            KeyFactory kf = KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
            PublicKey bce2 =  kf.generatePublic(eks);
            return bce2;
        }catch (Exception e){
            log.error("公钥串获取公钥证书对象异常",e);
            throw new AppBizException("9998","公钥串获取公钥证书对象异常,请联系开发人员");
        }
    }


    public static byte[] hexStr2Bytes(String src) {
        int m = 0, n = 0;
        if((src.length() & 1) != 0)
            src = "0" + src;
        int l = src.length() / 2;

        byte[] ret = new byte[l];
        for (int i = 0; i < l; ++i) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = Integer.decode(
                    "0x" + src.substring(i * 2, m) + src.substring(m, n))
                    .byteValue();
        }
        return ret;
    }

    public static PrivateKey  getPrivateKey(String priKey)  {
        try {
            log.info("通过私钥串获取私钥证书对象开始");
            Security.addProvider(new BouncyCastleProvider());
            byte[] prvBytes22 = Base64.decode(priKey);
            PKCS8EncodedKeySpec eks22 = new PKCS8EncodedKeySpec(prvBytes22);
            KeyFactory kf22= KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
            PrivateKey pvk= kf22.generatePrivate(eks22);
//            BCECPrivateKey bpve = (BCECPrivateKey) pvk;
//            String key = bpve.getS().toString(16).toUpperCase();
//            log.info("私钥(大整数)串16进制："+key);
            return pvk;
        }catch (Exception e){
            log.error("通过私钥串获取私钥证书对象异常",e);
            throw new AppBizException("9998","通过私钥串获取私钥证书对象异常,请联系开发人员");
        }
    }

        private String getSign(JSONObject jsonObject,String priKey){
            try {
                PrivateKey signPriKey = getPrivateKey(priKey);
                String msg = jsonObject.toJSONString();
                log.info("待加签字符串："+msg);

//                byte[] plainText = Sm3Utils.digest(msg.getBytes(Charset.forName("UTF-8")));
                byte[] plainText = msg.getBytes(Charset.forName("UTF-8"));
                log.info("SM3摘要串:" + byte2HexStr(plainText));
                Signature signature = Signature.getInstance(
                        GMObjectIdentifiers.sm2sign_with_sm3.toString()
                        , new BouncyCastleProvider());
                /*
                 * 签名
                 */
                // 签名需要使用私钥，使用私钥 初始化签名实例
                signature.initSign(signPriKey);

                signature.update(plainText);
                // 计算签名值
                byte[] signatureValue = signature.sign();
                String base64Sign = Base64.toBase64String(signatureValue);
                log.info("签名串:"+base64Sign);
                return  base64Sign;
            } catch (Exception e) {
                log.error("加签出现异常",e);
                throw new AppBizException("9998","加签出现异常,请联系开发人员");
            }
        }

    public static String byte2HexStr(byte[] b) {
        if(b == null)
            return "";
        String stmp = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
            // sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }
}
