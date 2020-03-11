package com.example.demo.jiami;

import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 功能：
 *
 * @author 2020/1/3
 * @author zoulinjun
 */
public class Test2 {

    public static void main(String[] args) throws  Exception {

        //------------------------------解析sm2公钥证书start---------------------------------------
        System.out.println("------------------------------解析sm2公钥证书---------------------------------------");
        String cerPath="E:\\zlj\\qb\\file\\4000370700.cer";
        X509Certificate x509Certificate = null;
        // 引入BC库
        Security.addProvider(new BouncyCastleProvider());
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509","BC");
        FileInputStream fileInputStream = new FileInputStream(cerPath);
        x509Certificate = (X509Certificate) certificateFactory.generateCertificate(fileInputStream);
        fileInputStream.close();
        System.out.println("证书序列号："+ x509Certificate.getSerialNumber());
        System.out.println("证书公钥："+ x509Certificate.getPublicKey());
        System.out.println("af0ea01e61236c863009b4174d1ec550de327db602ae49a29ebaa4c2583e6443".length());
        System.out.println("bac6735f06888d4516484d5bfc575ee7f5e8b6dd7f5bdc0d172b2568148a2f2e".length());
        PublicKey publicKey=x509Certificate.getPublicKey();
        BCECPublicKey bcecPublicKey = (BCECPublicKey)publicKey;
        //------------------------------解析sm2公钥证书end---------------------------------------




        //------------------------------生成秘钥对start---------------------------------------
        // 获取SM2椭圆曲线的参数
        final ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
        // 获取一个椭圆曲线类型的密钥对生成器
        final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
        // 使用SM2参数初始化生成器
        kpg.initialize(sm2Spec);
        // 使用SM2的算法区域初始化密钥生成器
        kpg.initialize(sm2Spec, new SecureRandom());
        // 获取密钥对
        KeyPair keyPair = kpg.generateKeyPair();
        PublicKey pk = keyPair.getPublic();
        System.out.println(new BASE64Encoder().encode(keyPair.getPublic().getEncoded()));
        System.out.println(new BASE64Encoder().encode(keyPair.getPrivate().getEncoded()));
        //---------------------------------生成蜜月对end-----------------------------------------






        //------------------------------解析sm2公钥原始串start---------------------------------------
        System.out.println("------通过公钥串获取对象------");
        String base64String="MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEDjIGzyssdjXchEWdJOKaH/LQsExdJdt093OsH30p\n" +
                "jIEehkcYhYrj/rlVxlL8fcWC6SQQ+iui8V1D7xMex0neTw==";
        byte[] publicBytes = new BASE64Decoder().decodeBuffer(base64String);
        X509EncodedKeySpec eks = new X509EncodedKeySpec(publicBytes);
        KeyFactory kf = KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        BCECPublicKey bce2 = (BCECPublicKey) kf.generatePublic(eks);
        System.out.println("证书2公钥："+ bce2);
        System.out.println("e3206cf2b2c7635dc84459d24e29a1ff2d0b04c5d25db74f773ac1f7d298c81".length());
        System.out.println("BAC6735F06888D4516484D5BFC575EE7F5E8B6DD7F5BDC0D172B2568148A2F2E".length());

        StringBuffer sb = new StringBuffer();
        String strInt = "";
        for(int i=0;i<publicBytes.length;i++){
            strInt = Integer.toHexString(publicBytes[i]&0xFF);
            if(strInt.length()<2){
                sb.append(0);
            }
            sb.append(strInt.toUpperCase());
        }
        System.out.println(sb.toString());
        //------------------------------解析sm2公钥原始串end---------------------------------------

        System.out.println("------通过私钥串获取对象------");
        String prvString22="MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQg4RaY3o3TLFV4qdnsyoGAjboEsvfp\n" +
                "Pg44f/k281w49tegCgYIKoEcz1UBgi2hRANCAAQOMgbPKyx2NdyERZ0k4pof8tCwTF0l23T3c6wf\n" +
                "fSmMgR6GRxiFiuP+uVXGUvx9xYLpJBD6K6LxXUPvEx7HSd5P";
        byte[] prvBytes22 = new BASE64Decoder().decodeBuffer(prvString22);
        PKCS8EncodedKeySpec eks22 = new PKCS8EncodedKeySpec(prvBytes22);
        KeyFactory kf22= KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        PrivateKey pvk= kf22.generatePrivate(eks22);
        BCECPrivateKey bpve = (BCECPrivateKey) pvk;
        System.out.println("私钥："+ bpve.getS()+":::16hex:::"+bpve.getS().toString(16));

    }
}
