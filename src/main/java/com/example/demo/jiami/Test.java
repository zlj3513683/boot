package com.example.demo.jiami;

import com.example.demo.sm2.Util;
import org.bouncycastle.util.encoders.Base64;

import java.math.BigInteger;

/**
 * 功能：
 *
 * @author 2020/1/3
 * @author zoulinjun
 */
public class Test {

    public static void main(String[] args) {
        byte[] a = Util.hexStringToBytes("40C122501862F6665606095C847C1F8FDF07ED11C052B537D311781A146B68374E02B36488B209AFF610A703C2E89EA44F16E10D4D4CED9B720CA1684E7D9838");
        String aa = Base64.toBase64String(a);
        System.out.println(aa);
        BigInteger bigInteger = new BigInteger("48878000380543384017655350912322932876330940168866711718430053412807167221828");
        System.out.println(Util.getHexString(Util.byteConvert32Bytes(bigInteger)));;
    }
}

