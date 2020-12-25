package com.example.demo.jdk.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/4
 */
public class Zhongduan {

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

        public synchronized void add2(){
            System.out.println(Thread.currentThread().getName() + "获取到锁拉");
            while (true){
                i++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Thread t1 =new Thread(bank::add2,"t1");
        Thread t2 =new Thread(bank::add2,"t2");
        t1.start();
        Thread.sleep(1000);
        t2.start();
        t2.interrupt();
        t1.join();
        t2.join();
        System.out.println(bank.i);
    }



}
