package com.example.demo.jdk.thread;

/**
 * 功能：可重入锁
 *
 * @author zoulinjun
 * @date 2020/12/3
 */
public class LockTestCcr implements Runnable{


    final CanLock lock = new CanLock();

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
        LockTestCcr cr = new LockTestCcr();
        new Thread(cr).start();
    }

}
