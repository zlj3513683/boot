package com.example.demo.jdk.jvm;

import java.io.IOException;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/11
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String str1 = "111";
        String str2 = "111";
        System.out.println(str1 == str2);
        String str3 = "11" + 1;
        System.out.println(str1 == str3);
        String str4 = "11";
        String str5 = str4 + 1;
        System.out.println(str1 == str5);
        final String str6 = "11";
        String str7 = str6 + 1;
        System.out.println(str1 == str7);
        String str8 = new String("111");
        String str9 = new String("111");
        System.out.println(str8 == str9);
        String s1 = "ab" + "cd";
        String s2 = "abc" + "d";
        System.out.println("s1==s2:" + s1 == s2);
        String s3 = "111" + 2;
        String s4 = "11" + 22;
        System.out.println("s3==s4:" + s3 == s4);

        Test test = new Test();
//        System.in.read();
    }

}
