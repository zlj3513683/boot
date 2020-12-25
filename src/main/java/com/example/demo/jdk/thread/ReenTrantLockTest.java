package com.example.demo.jdk.thread;

import sun.security.krb5.internal.Ticket;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能：ReentrantLock的demo
 *
 * @author zoulinjun
 * @date 2020/12/3
 */
public class ReenTrantLockTest {

    private int ijj;

    static class Ticket{
        int count = 100000;
        private final ReentrantLock lock = new ReentrantLock(true);

        public void mp(){
            lock.lock();
            try{
//                System.out.println(lock.tryLock());
                System.out.println(Thread.currentThread().getName() + "获取了锁");
                for (int i = 0; i < 10000; i++) {
                    count--;
                }
            }finally {
                lock.unlock();
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {
        Ticket ticket = new Ticket();
        Thread t1 = new Thread(ticket::mp,"t1");
        Thread t2 = new Thread(ticket::mp,"t2");
        Thread t3 = new Thread(ticket::mp,"t3");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(ticket.count);
    }

}
