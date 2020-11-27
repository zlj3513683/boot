package com.example.demo.test;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/11/2
 */
public class DmkTest {
    public static void main(String[] args) {
        System.out.println("1");
        {
            for (int i = 0; i < 100; i++) {
                System.out.println("2=" + i);
            }
        }
        System.out.println("3");
    }
}
