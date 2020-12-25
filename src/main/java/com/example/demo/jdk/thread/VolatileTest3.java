package com.example.demo.jdk.thread;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/4
 */
public class VolatileTest3 extends Thread{

    int i = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        VolatileTest3 vt = new VolatileTest3();
        vt.start();
        Thread.sleep(2000);
        vt.i = 100;
        System.out.println("stope" + vt.i);
    }


}
