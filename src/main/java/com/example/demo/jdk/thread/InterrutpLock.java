package com.example.demo.jdk.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能：ReentrantLock中断阻塞中的线程
 * lock() 不可中断线程
 * tryLock() 也不可以中断
 * tryLock(100,TimeUnit.MINUTES) throws InterruptedException 可中断正在等待锁的线程
 * lockInterruptibly() throws InterruptedException 也可中断正在等待锁的线程,可以理解为tryLock的超时时间设置为无限时间
 *
 * @author zoulinjun
 * @date 2020/12/7
 */
public class InterrutpLock {

    static class Bank {

        private volatile static int count = 0;
        private final Lock lock = new ReentrantLock();

        public void add1() {
            lock.lock();
            try {
                System.out.println("线程" + Thread.currentThread().getName() + "开始执行");
                while (true) {
                    count++;
                }
            } finally {
                lock.unlock();
            }

        }
        public void add2() {
            if(lock.tryLock()){
                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "开始执行");
                    while (true) {
                        count++;
                    }
                } finally {
                    lock.unlock();
                }
            }

        }
        public void add3() {
            try {
                if(lock.tryLock(100,TimeUnit.MINUTES)){
                    try {
                        System.out.println("线程" + Thread.currentThread().getName() + "开始执行");
                        while (true) {
                            count++;
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("线程" + Thread.currentThread().getName() + "提前结束，真是个悲伤的story");
                e.printStackTrace();
            }

        }
        public void add4() {
            try {
                lock.lockInterruptibly();
                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "开始执行");
                    while (true) {
                        count++;
                    }
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println("线程" + Thread.currentThread().getName() + "提前结束，真是个悲伤的story");
                e.printStackTrace();
            }

        }

        public void add5() {
            lock.tryLock();
            try {
                for (int i = 0; i < 1000; i++) {

                    System.out.println("线程" + Thread.currentThread().getName() + "开始执行");
                }
//                while (true) {
//                    count++;
//                }
            } finally {
                lock.unlock();
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank();

//        Thread thread1 = new Thread(bank::add1,"t1");
//        Thread thread2 = new Thread(bank::add1,"t2");
//        Thread thread3 = new Thread(bank::add1,"t3");
//        Thread thread1 = new Thread(bank::add2,"t1");
//        Thread thread2 = new Thread(bank::add2,"t2");
//        Thread thread3 = new Thread(bank::add2,"t3");
        Thread thread1 = new Thread(bank::add3,"t1");
        Thread thread2 = new Thread(bank::add3,"t2");
        Thread thread3 = new Thread(bank::add3,"t3");
//        Thread thread1 = new Thread(bank::add4,"t1");
//        Thread thread2 = new Thread(bank::add4,"t2");
//        Thread thread3 = new Thread(bank::add4,"t3");
//
        thread1.start();
        Thread.sleep(1);
        thread2.start();
        Thread.sleep(1);
        thread3.start();
        Thread.sleep(1);
        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();
        System.out.println("中断线程");

        //测试add5
//        Thread thread4 = new Thread(bank::add5,"t4");
//        Thread thread5 = new Thread(bank::add5,"t25");
//        Thread thread6 = new Thread(bank::add5,"t6");

//        thread4.start();
//        Thread.sleep(1);
//        thread5.start();
//        Thread.sleep(1);
//        thread6.start();
//        Thread.sleep(1);



    }

}
