package com.example.demo.jdk.string;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/9
 */
public class StringTest {

    public static void main(String[] args) {
        String str1 = "string1";
        String str2 = "string";
        String str3 = str2 + 1;
        System.out.println(str1 == str3);
        System.out.println(str1.hashCode());
        System.out.println(str3.hashCode());

        String t1 = new String("string");
        String t2 = new String("string");
        System.out.println(t1 == t2);
        System.out.println(t1.equals(t2));
    }

}
