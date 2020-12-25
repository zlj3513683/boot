package com.example.demo.jdk.thread;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/3
 */
public class ThreadStaticTest2 implements Runnable {

    static int count = 0;

    public synchronized static void  add(){
        for (int i = 0; i <10000 ; i++) {
            count++;
        }
    }

    @Override
    public void run() {
        try {
            add();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread threadStaticTest1 = new Thread(new ThreadStaticTest2());
        Thread threadStaticTest2 = new Thread(new ThreadStaticTest2());
        threadStaticTest1.start();
        threadStaticTest2.start();
        threadStaticTest1.join();
        threadStaticTest2.join();
        System.out.println(count);

    }

}
