package com.example.demo.jdk;

import com.example.demo.jdk.object.Test;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/11/30
 */
public class Jdktest {

    public static void main(String[] args) {

        Test t1 = new Test();
        Test t2 = new Test();
        System.out.println(t1.equals(t2));
        t1.notify();

    }

}
