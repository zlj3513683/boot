package com.example.demo.jdk.thread;

/**
 * 功能：volatile的作用，保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
 *
 * @author zoulinjun
 * @date 2020/12/4
 */
public class VolatileTest extends Thread {

//    volatile boolean flag = false;
    boolean flag = false;
    int i = 0;

    @Override
    public void run() {
        while (!flag) {
            i++;
        }
    }

    public static void main(String[] args) throws Exception {
        VolatileTest vt = new VolatileTest();
        vt.start();
        Thread.sleep(2000);
        vt.flag = true;
        System.out.println("stope" + vt.i);
    }
}
