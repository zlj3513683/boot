package com.example.demo.jdk.concureent;

import com.example.demo.jdk.thread.InterrutpLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/25
 */
public class ZhongDuanTest {
    static class Bank {

        private volatile static int count = 0;
        private final Lock lock = new ReentrantLock();

        public void add1() {
            lock.lock();
            try {
                System.out.println("线程" + Thread.currentThread().getName() + "开始执行");
                for (int i = 0; i < 1000000; i++) {
                    System.out.println("线程" + Thread.currentThread().getName() + "执行次数" + i);
                }
            } finally {
                lock.unlock();
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {

        ZhongDuanTest.Bank bank = new ZhongDuanTest.Bank();

        Thread thread1 = new Thread(bank::add1,"t1");
        Thread thread2 = new Thread(bank::add1,"t2");
        Thread thread3 = new Thread(bank::add1,"t3");
//
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        Thread.sleep(1);
        thread3.start();
        Thread.sleep(1);
//        thread1.interrupt();
//        thread2.interrupt();
//        thread3.interrupt();
        System.out.println("中断线程");




    }

}

