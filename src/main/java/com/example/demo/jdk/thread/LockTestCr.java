package com.example.demo.jdk.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能：不可重入锁
 *
 * @author zoulinjun
 * @date 2020/12/3
 */
public class LockTestCr implements Runnable{


    final Lock lock = new Lock();

    public void methodA() throws InterruptedException {
        lock.lock();
        System.out.println("执行A方法");
        methodB();
        lock.unlock();
    }

    public void methodB() throws InterruptedException {
        lock.lock();
        System.out.println("执行B方法");
        lock.unlock();
    }

    @Override
    public void run() {
        try {
            methodA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException  {
        LockTestCr cr = new LockTestCr();
        new Thread(cr).start();
    }

}
