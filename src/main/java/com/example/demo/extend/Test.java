package com.example.demo.extend;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/4/10
 */
public class Test {

    public static void main(String[] args) {

        try {
            String s  = URLEncoder.encode("yA1+9DULSgC27/NtvTn6sg==","utf-8");
            System.out.println(s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Son s = new Son();
        s.setSs("111");
        s.setPp("222");
        Person p = (Person) s;
        Son s2 = (Son) p;
        System.out.println(s2.toString());
        try {
            Son  son = Son.class.newInstance();
            son.setPp("123");
            System.out.println(son);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
