package com.example.demo.jdk.thread;

import java.util.concurrent.TimeUnit;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/4
 */
public class InterruptSleep implements Runnable{
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("开始睡了");
                TimeUnit.SECONDS.sleep(3);
            }
        } catch (InterruptedException e) {
            boolean isInterrupt = Thread.interrupted();
            //中断状态被复位
            System.out.println("中断状态:" + isInterrupt);
        }
    }

    public static void main(String[] args) throws Exception{
        Thread t = new Thread(new InterruptSleep());
        t.start();
        TimeUnit.SECONDS.sleep(5);
        t.interrupt();
        System.out.println("interrupted is: " + t.isInterrupted());
    }


}
