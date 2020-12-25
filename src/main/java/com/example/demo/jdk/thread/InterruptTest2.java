package com.example.demo.jdk.thread;

/**
 * 功能：sleep中断线程demo
 *
 * @author zoulinjun
 * @date 2020/12/7
 */
public class InterruptTest2 {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("进入线程开始执行中!");
                try {
                    System.out.println("线程阻塞中!");
                    Thread.sleep(100000);
                    System.out.println("线程阻塞结束!");
                } catch (InterruptedException e) {
                    System.out.println("这个线程凉凉了,阻塞被结束了!");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
        System.out.println("中断线程");

    }

}
