package com.example.demo.jdk.thread;

/**
 * 功能：synchronized同步静态方法，锁住了Class对象
 *
 * @author zoulinjun
 * @date 2020/12/3
 */
public class ThreadKuaiTest implements Runnable{

    static ThreadKuaiTest threadKuaiTest = new ThreadKuaiTest();
    static int count  = 0;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "execute");
        synchronized (threadKuaiTest){
            for (int i = 0; i < 100000; i++) {
                count++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(threadKuaiTest,"t1");
        Thread t2 = new Thread(threadKuaiTest,"t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);

    }
}
