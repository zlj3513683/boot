package com.example.demo.jdk.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能：线程中断
 *
 * @author zoulinjun
 * @date 2020/12/4
 */
public class LockTest {

    static class Bank{
        volatile int i = 0;
        final Lock lock = new ReentrantLock();

        public void add(){
            if(lock.tryLock()){
                System.out.println(Thread.currentThread().getName() + "获取到锁拉");
                try{
                    for (int j = 0; j < 10000; j++) {
                        i++;
                    }
                }finally {
                    lock.unlock();
                }
            }else{
                System.out.println(Thread.currentThread().getName() + "未获取到锁");
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Thread t1 =new Thread(bank::add,"t1");
        Thread t2 =new Thread(bank::add,"t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(bank.i);
    }

}
