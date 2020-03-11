package com.example.demo.jiami;

import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;
import sun.misc.BASE64Decoder;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

/**
 * 功能：
 *
 * @author 2020/1/2
 * @author zoulinjun
 */
public class MySm2Utils {

    public static void main(String[] args) throws Exception {
        System.out.println("xxxxxxxx");
        String base64String="MIIC3zCCAoOgAwIBAgIFQAA3BwAwDAYIKoEcz1UBg3UFADBhMQ\n" +
                "swCQYDVQQGEwJDTjEwMC4GA1UECgwnQ2hpbmEgRmluYW5jaWFs\n" +
                "IENlcnRpZmljYXRpb24gQXV0aG9yaXR5MSAwHgYDVQQDDBdDRk\n" +
                "NBIEFDUyBURVNUIFNNMiBPQ0EzMTAeFw0xNzA0MjYxMDQyNDda\n" +
                "Fw0yMjA0MjYxMDQyNDdaMIGDMQswCQYDVQQGEwJDTjERMA8GA1\n" +
                "UECgwIT0NBMzFTTTIxFTATBgNVBAsMDHNoYW5naGFpVGVjaDEZ\n" +
                "MBcGA1UECwwQT3JnYW5pemF0aW9uYWwtMjEvMC0GA1UEAwwmU0\n" +
                "hUZWNoQOS4reWbvemTtuiBlEA4OTEzMTAwMDA3MzYyMzk4QDIw\n" +
                "WTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAASvDqAeYSNshjAJtB\n" +
                "dNHsVQ3jJ9tgKuSaKeuqTCWD5kQ7rGc18GiI1FFkhNW/xXXuf1\n" +
                "6Lbdf1vcDRcrJWgUii8uo4IBATCB/jBGBggrBgEFBQcBAQQ6MD\n" +
                "gwNgYIKwYBBQUHMAGGKmh0dHA6Ly8yMTAuNzQuNDIuMTE6ODA4\n" +
                "NS9vY3NwX3NlcnZlci9vY3NwLzAfBgNVHSMEGDAWgBQEx7z5WQ\n" +
                "FpPow0NiBiGDzevLW7DDAMBgNVHRMBAf8EAjAAMDcGA1UdHwQw\n" +
                "MC4wLKAqoCiGJmh0dHA6Ly8yMTAuNzQuNDIuMy9PQ0EzMS9TTT\n" +
                "IvY3JsNTcuY3JsMA4GA1UdDwEB/wQEAwIGwDAdBgNVHQ4EFgQU\n" +
                "jAXTff6jIROxvk4nnewh3SLz7LwwHQYDVR0lBBYwFAYIKwYBBQ\n" +
                "UHAwIGCCsGAQUFBwMEMAwGCCqBHM9VAYN1BQADSAAwRQIgdhJs\n" +
                "e3F8tYSZRNv0B/VtkVh7t5FS61ZzcMiNHq/Ebm4CIQCCt5Fkh3\n" +
                "ACiybRgGb+wKbh9gWhZTr6O/qZeQlb7XXfrw==";
        byte[] publicBytes = new BASE64Decoder().decodeBuffer(base64String);

//        String cerPath="E:\\4000370700.cer";
        String cerPath="E:\\zlj\\qb\\file\\test-cert.cer";
        X509Certificate x509Certificate = null;
        // 引入BC库
        Security.addProvider(new BouncyCastleProvider());
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509","BC");
        FileInputStream fileInputStream = new FileInputStream(cerPath);
        x509Certificate = (X509Certificate) certificateFactory.generateCertificate(fileInputStream);
        fileInputStream.close();

        System.out.println("证书序列号："+ x509Certificate.getSerialNumber());
        System.out.println("证书公钥："+ x509Certificate.getPublicKey());

        PublicKey publicKey=x509Certificate.getPublicKey();
        BCECPublicKey bcecPublicKey = (BCECPublicKey)publicKey;
    }

    /*private static X9ECParameters x9ECParameters = GMNamedCurves.getByName("sm2p256v1");
    private static ECDomainParameters ecDomainParameters = new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN());


    private final static int SM2_KEY_LEN = 32;
    public static ECPublicKey encodEcPublicKey(byte[] publickey)
            throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException {
        KeyFactory factory = KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        if (publickey.length != SM2_KEY_LEN * 2)
        {
            throw new RuntimeException("err key. ");
        }
        BigInteger X = new BigInteger(1, Arrays.copyOfRange(publickey, 0, SM2_KEY_LEN));
        BigInteger Y = new BigInteger(1, Arrays.copyOfRange(publickey, SM2_KEY_LEN, SM2_KEY_LEN * 2));
        ECPoint point = CURVE.createPoint(X, Y);
        ECPublicKeySpec keySpec = new ECPublicKeySpec(point, ecDomainParameters);
        return (ECPublicKey) factory.generatePublic(keySpec);
    }

    public static ECPrivateKey encodEcPrivateKeyKey(byte[] privatekey)
            throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException {
        KeyFactory factory = KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        if (privatekey.length != SM2_KEY_LEN){
            throw new RuntimeException("err key. ");
        }
        ECPrivateKeySpec keySpec = new ECPrivateKeySpec(new BigInteger(1, privatekey), ecDomainParameters);
        return (ECPrivateKey) factory.generatePrivate(keySpec);
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        keyPairGenerator.initialize(ecDomainParameters, new SecureRandom());
        return keyPairGenerator.generateKeyPair();

    }*/

}
