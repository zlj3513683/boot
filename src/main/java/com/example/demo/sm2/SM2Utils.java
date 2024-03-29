package com.example.demo.sm2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Enumeration;




public class SM2Utils {


    /*public static byte[] encrypt(byte[] publicKey, byte[] data) throws IOException {

        if (publicKey == null || publicKey.length == 0) {
            return null;
        }

        if (data == null || data.length == 0) {
            return null;
        }

        byte[] source = new byte[data.length];
        System.arraycopy(data, 0, source, 0, data.length);

        Cipher cipher = new Cipher();
        com.zpc.cryptography.SM2 sm2 = com.zpc.cryptography.SM2.Instance();
        ECPoint userKey = sm2.ecc_curve.decodePoint(publicKey);

        ECPoint c1 = cipher.Init_enc(sm2, userKey);
        cipher.Encrypt(source);
        byte[] c3 = new byte[32];
        cipher.Dofinal(c3);

        DERInteger x = new DERInteger(c1.getX().toBigInteger());
        DERInteger y = new DERInteger(c1.getY().toBigInteger());
        DEROctetString derDig = new DEROctetString(c3);
        DEROctetString derEnc = new DEROctetString(source);
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(x);
        v.add(y);
        v.add(derDig);
        v.add(derEnc);
        DERSequence seq = new DERSequence(v);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DEROutputStream dos = new DEROutputStream(bos);
        dos.writeObject(seq);
        return bos.toByteArray();
    }

    public static byte[] decrypt(byte[] privateKey, byte[] encryptedData) throws IOException {

        if (privateKey == null || privateKey.length == 0) {
            return null;
        }

        if (encryptedData == null || encryptedData.length == 0) {
            return null;
        }

        byte[] enc = new byte[encryptedData.length];
        System.arraycopy(encryptedData, 0, enc, 0, encryptedData.length);

        SM2 sm2 = SM2.Instance();
        BigInteger userD = new BigInteger(1, privateKey);

        ByteArrayInputStream bis = new ByteArrayInputStream(enc);
        ASN1InputStream dis = new ASN1InputStream(bis);
        ASN1Primitive derObj = dis.readObject();
        //DERObject derObj = dis.readObject();
        ASN1Sequence asn1 = (ASN1Sequence) derObj;
        //DERInteger x = (DERInteger) asn1.getObjectAt(0);
        //DERInteger y = (DERInteger) asn1.getObjectAt(1);
        ASN1Integer x = (ASN1Integer) asn1.getObjectAt(0);
        ASN1Integer y = (ASN1Integer) asn1.getObjectAt(1);
        ECPoint c1 = sm2.ecc_curve.createPoint(x.getValue(), y.getValue(), true);

        Cipher cipher = new Cipher();
        cipher.Init_dec(userD, c1);
        DEROctetString data = (DEROctetString) asn1.getObjectAt(3);
        enc = data.getOctets();
        cipher.Decrypt(enc);
        byte[] c3 = new byte[32];
        cipher.Dofinal(c3);
        return enc;
    }

    *//**
     * 签名
     *
     * @param userId
     * @param privateKey
     * @param sourceData
     * @return
     * @throws IOException
     *//*
    public static byte[] sign(byte[] userId, byte[] privateKey, byte[] sourceData) throws IOException {

        if (privateKey == null || privateKey.length == 0) {
            return null;
        }

        if (sourceData == null || sourceData.length == 0) {
            return null;
        }

        com.zpc.cryptography.SM2 sm2 = com.zpc.cryptography.SM2.Instance();
        BigInteger userD = new BigInteger(privateKey);
        //System.out.println("userD: " + userD.toString(16));
        //System.out.println("");

        ECPoint userKey = sm2.ecc_point_g.multiply(userD);
        //System.out.println("椭圆曲线点X: " + userKey.getX().toBigInteger().toString(16));
        //System.out.println("椭圆曲线点Y: " + userKey.getY().toBigInteger().toString(16));
        //System.out.println("");


        byte[] z = sm2.sm2GetZ(userId, userKey);
        //System.out.println("SM3摘要Z: " + Util.getHexString(z));
        //System.out.println("");

        //System.out.println("M: " + Util.getHexString(sourceData));
        //System.out.println("");

        SM3Digest sm3 = new SM3Digest();
        sm3.update(z, 0, z.length);
        sm3.update(sourceData, 0, sourceData.length);
        byte[] md = new byte[32];
        sm3.doFinal(md, 0);

        //System.out.println("SM3摘要值: " + Util.getHexString(md));
        //System.out.println("");

        SM2Result sm2Result = new SM2Result();
        sm2.sm2Sign(md, userD, userKey, sm2Result);
        //System.out.println("r: " + sm2Result.r.toString(16));
        //System.out.println("s: " + sm2Result.s.toString(16));
        //System.out.println("");

        DERInteger d_r = new DERInteger(sm2Result.r);
        DERInteger d_s = new DERInteger(sm2Result.s);
        ASN1EncodableVector v2 = new ASN1EncodableVector();
        v2.add(d_r);
        v2.add(d_s);
        DERSequence sign = new DERSequence(v2);
        //DERObject sign = new DERSequence(v2);
        //byte[] signdata = sign.getDEREncoded();
        byte[] signdata = sign.getEncoded();

        return signdata;
    }

    @SuppressWarnings("unchecked")
    public static boolean verifySign(byte[] userId, byte[] publicKey, byte[] sourceData, byte[] signData) throws IOException {

        if (publicKey == null || publicKey.length == 0) {
            return false;
        }

        if (sourceData == null || sourceData.length == 0) {
            return false;
        }

        com.zpc.cryptography.SM2 sm2 = com.zpc.cryptography.SM2.Instance();
        ECPoint userKey = sm2.ecc_curve.decodePoint(publicKey);

        SM3Digest sm3 = new SM3Digest();
        byte[] z = sm2.sm2GetZ(userId, userKey);
        sm3.update(z, 0, z.length);
        sm3.update(sourceData, 0, sourceData.length);
        byte[] md = new byte[32];
        sm3.doFinal(md, 0);
        //System.out.println("SM3摘要值: " + Util.getHexString(md));
        //System.out.println("");

        ByteArrayInputStream bis = new ByteArrayInputStream(signData);
        ASN1InputStream dis = new ASN1InputStream(bis);
        //DERObject derObj = dis.readObject();
        ASN1Primitive derObj = dis.readObject();
        Enumeration<ASN1Integer> e = ((ASN1Sequence) derObj).getObjects();
        BigInteger r = e.nextElement().getValue();
        BigInteger s = e.nextElement().getValue();
        SM2Result sm2Result = new SM2Result();
        sm2Result.r = r;
        sm2Result.s = s;
        //System.out.println("r: " + sm2Result.r.toString(16));
        //System.out.println("s: " + sm2Result.s.toString(16));
        //System.out.println("");


        sm2.sm2Verify(md, userKey, sm2Result.r, sm2Result.s, sm2Result);
        return sm2Result.r.equals(sm2Result.R);
    }

    public static void main(String[] args) throws Exception {


        String plainText = "message digest";
        //1.源数据数组
        byte[] sourceData = plainText.getBytes();

        // 国密规范测试私钥
        String prik = "128B2FA8BD433C6C068C8D803DFF79792A519A55171B1B650C23661D15897263";
        //2.私钥先转十六进制，然后进行Base64编码
        String prikS = new String(Base64.encode(Util.hexToByte(prik)));
        System.out.println("转换后的私钥：prikS====" + prikS);
        //System.out.println("");

        // 国密规范测试用户ID
        String userId = "ALICE123@YAHOO.COM";

        //获取userId十六进制字符串
        System.out.println("十六进制userId: " + Util.getHexString(userId.getBytes()));
        //System.out.println("");

        //System.out.println("签名: ");
        //3.用userId和私钥，对明文数据签名(userid、prik、sourceData)
        byte[] c = SM2Utils.sign(userId.getBytes(), Base64.decode(prikS.getBytes()), sourceData);
        System.out.println("SM2签名后值====" + Util.getHexString(c));
        //System.out.println("");

        // 国密规范测试公钥
        String pubk = "040AE4C7798AA0F119471BEE11825BE46202BB79E2A5844495E97C04FF4DF2548A7C0240F88F1CD4E16352A73C17B7F16F07353E53A176D684A9FE0C6BB798E857";
        String pubkS = new String(Base64.encode(Util.hexToByte(pubk)));
        System.out.println("转换后的公钥pubkS====" + pubkS);
        //System.out.println("");


        //System.out.println("验签: ");
        //4.用公钥进行验签(userId、pubk、sourceData、签名数据c)
        boolean vs = SM2Utils.verifySign(userId.getBytes(), Base64.decode(pubkS.getBytes()), sourceData, c);
        System.out.println("验签结果: " + vs);
        //System.out.println("");

        //System.out.println("加密: ");
        //5.SM2加密算法
        byte[] cipherText = SM2Utils.encrypt(Base64.decode(pubkS.getBytes()), sourceData);
        System.out.println("SM2加密后结果===" + new String(Base64.encode(cipherText)));
        System.out.println("");

        //System.out.println("解密: ");
        //6.SM2解密算法
        plainText = new String(SM2Utils.decrypt(Base64.decode(prikS.getBytes()), cipherText));
        System.out.println("解密后获取的结果===" + plainText);

    }*/
}