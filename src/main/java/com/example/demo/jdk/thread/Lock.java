package com.example.demo.jdk.thread;

/**
 * 功能：不可重入锁
 *
 * @author zoulinjun
 * @date 2020/12/3
 */
public class Lock {

    private static boolean locked = false;

    public synchronized  void lock() throws InterruptedException {
        while (locked){
            wait();
        }
        locked = true;
    }

    public synchronized  void unlock(){
        locked = false;
        notify();
    }


}
