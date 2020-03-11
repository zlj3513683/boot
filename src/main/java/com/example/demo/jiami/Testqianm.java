package com.example.demo.jiami;

import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import sun.misc.BASE64Decoder;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 功能：
 *
 * @author 2020/1/9
 * @author zoulinjun
 */
public class Testqianm {
    public static void main(String[] args) throws Exception{
        String prvString22="MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQg4RaY3o3TLFV4qdnsyoGAjboEsvfp\n" +
                "Pg44f/k281w49tegCgYIKoEcz1UBgi2hRANCAAQOMgbPKyx2NdyERZ0k4pof8tCwTF0l23T3c6wf\n" +
                "fSmMgR6GRxiFiuP+uVXGUvx9xYLpJBD6K6LxXUPvEx7HSd5P";
        Security.addProvider(new BouncyCastleProvider());
        byte[] prvBytes22 = new BASE64Decoder().decodeBuffer(prvString22);
        PKCS8EncodedKeySpec eks22 = new PKCS8EncodedKeySpec(prvBytes22);
        KeyFactory kf22= KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        PrivateKey privateKey= kf22.generatePrivate(eks22);

//        Security.addProvider(new BouncyCastleProvider());
//        final ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
//        // 获取一个椭圆曲线类型的密钥对生成器
//        final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
//        // 使用SM2参数初始化生成器
//        kpg.initialize(sm2Spec);
//        // 使用SM2的算法区域初始化密钥生成器
//        kpg.initialize(sm2Spec, new SecureRandom());
//        // 获取密钥对
//        KeyPair keyPair = kpg.generateKeyPair();
//        PublicKey publicKey = keyPair.getPublic();
//        PrivateKey privateKey = keyPair.getPrivate();

// 生成SM2sign with sm3 签名验签算法实例
        Signature signature = Signature.getInstance(
                GMObjectIdentifiers.sm2sign_with_sm3.toString()
                , new BouncyCastleProvider());

        /*
         * 签名
         */
// 签名需要使用私钥，使用私钥 初始化签名实例
        signature.initSign(privateKey);
//        String hex = "09CAA89A0A7563AF97E9107ED5CB2A81CC8EE82E2DD5425D5E041E8B1FC2821D";
        String hex = "09CAA89A0A7563AF97E9107ED5CB2A81CC8EE82E2DD5425D5E041E8B1FC2821D";
//        String hex = "09CAA89A0A7563AF97E9107ED5CB2A81CC8EE82E2DD5425D5E041E8B1FC2821D";
//        09CAA89A0A7563AF97E9107ED5CB2A81CC8EE82E2DD5425D5E041E8B1FC2821D
//        5B9ECA88C9CE93F615F0AEA00FF8DB1336EE4BAFCDA79DADA085AFF6B11C7938


//        5B9ECA88C9CE93F615F0AEA00FF8DB1336EE4BAFCDA79DADA085AFF6B11C7938
//        09CAA89A0A7563AF97E9107ED5CB2A81CC8EE82E2DD5425D5E041E8B1FC2821D


// 签名原文
//        byte[] plainText = "Hello world".getBytes(StandardCharsets.UTF_8);
        byte[] plainText = hexStr2Bytes(hex);
// 写入签名原文到算法中
        signature.update(plainText);
// 计算签名值
        byte[] signatureValue = signature.sign();
        System.out.println("signature: \n" + Hex.toHexString(signatureValue));
        System.out.println("signature: \n" + Hex.toHexString(signatureValue).length());
        System.out.println("signature: \n" + Base64.toBase64String(signatureValue));

        String base64String="MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEDjIGzyssdjXchEWdJOKaH/LQsExdJdt093OsH30p\n" +
                "jIEehkcYhYrj/rlVxlL8fcWC6SQQ+iui8V1D7xMex0neTw==";
        byte[] publicBytes = new BASE64Decoder().decodeBuffer(base64String);
        X509EncodedKeySpec eks = new X509EncodedKeySpec(publicBytes);
        KeyFactory kf = KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        BCECPublicKey publicKey = (BCECPublicKey) kf.generatePublic(eks);
        /*
         * 验签
         */
// 签名需要使用公钥，使用公钥 初始化签名实例
        signature.initVerify(publicKey);
// 写入待验签的签名原文到算法中
        signature.update(plainText);
// 验签
        System.out.println("Signature verify result: " + signature.verify(signatureValue));
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


    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
}
