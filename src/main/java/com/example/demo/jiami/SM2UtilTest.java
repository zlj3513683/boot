package com.example.demo.jiami;

import com.example.demo.sm2.Util;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Arrays;

/**
 * 功能：
 *
 * @author 2019/12/31
 * @author zoulinjun
 */
public class SM2UtilTest {

    /** 元消息串 */
    private static String M = "哈哈哈，&*&…………&、、//\\!@#$%^&*()物品woyebuzhidaowozijiqiaodesha!@#$%^&*())))))ooooooooppppppppppppppppppplllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkffffffffffffffffffffffffffffffffffffff";

    public static void main(String[] args) {

       /* String privateKey = "nbXbTHQUGrAzEywesR7Wpcd8D3ZS3wcVpnNdYGph/OU=";
        SM2KeyPair keyPair = new SM2KeyPair(G.multiply(d).normalize(), d);
        if (checkPublicKey(keyPair.getPublicKey())) {
            System.out.println("generate key successfully");
            return keyPair;
        } else {
            System.err.println("generate key failed");
            return null;
        }*/

        SM2Util sm2 = new SM2Util();
        SM2KeyPair keyPair = sm2.generateKeyPair();
        byte[] data = sm2.encrypt(M,keyPair.getPublicKey());
        System.out.println("data is:"+Arrays.toString(data));
        sm2.decrypt(data, keyPair.getPrivateKey());//71017045908707391874054405929626258767106914144911649587813342322113806533034

        System.out.println("1122331");

        String pub = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgSPxIoAfR8iMD2uSmxGSsLdvxg7U0wZNi9vY8gtPHMNKgCgYIKoEcz1UBgi2hRANCAARTxKkOnEFUUzDrezrXlBczj0pdFWbPlLON17Qi2JkQ6bs6JpX4UJrVtIVc1nWAayBnJ2CnAPSIDCRb8Rp+ICie";
        byte[] bb = Base64.decodeBase64(pub);
        System.out.println(Util.byteConvertInteger(bb));
        char[] c = Util.encodeHex(bb);
        System.out.println(c);
    }


}
