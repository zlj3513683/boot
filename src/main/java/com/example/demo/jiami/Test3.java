package com.example.demo.jiami;

import com.example.demo.sm2.Util;
import org.bouncycastle.util.encoders.Base64;

/**
 * 功能：
 *
 * @author 2020/1/3
 * @author zoulinjun
 */
public class Test3 {
    public static void main(String[] args) {
//        9DB5DB4C74141AB033132C1EB11ED6A5C77C0F7652DF0715A6735D606A61FCE5
//        9DB5DB4C74141AB033132C1EB11ED6A5C77C0F7652DF0715A6735D606A61FCE5
//        21878D43B074D1D14269AEB716D0FB2915633751D487CDEBCCDF591C0078EA23
//        String pri = "IYeNQ7B00dFCaa63FtD7KRVjN1HUh83rzN9ZHAB46iM=";
        String pri = "106371296994195197367260605747605707356111987942057244347196443635385219655832";
        System.out.println(Util.getHexString(pri.getBytes()));
        byte[] b = Base64.decode(pri);
        System.out.println(Util.getHexString(b));
    }
}
