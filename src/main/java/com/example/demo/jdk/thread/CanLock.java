package com.example.demo.jdk.thread;

/**
 * 功能：可重入锁实现
 *
 * @author zoulinjun
 * @date 2020/12/3
 */
public class CanLock {

    boolean locked  = false;
    Thread threadBy = null;
    int lockCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        while (locked && threadBy != thread){
            wait();
        }
        locked = true;
        threadBy = thread;
        lockCount++;
    }

    public synchronized void unlock(){
        if(Thread.currentThread() == this.threadBy){
            lockCount--;
            if(lockCount == 0){
                locked = false;
                notify();
            }
        }
    }


}
