package com.example.demo.jdk.thread;

/**
 * 功能：volatile不能保证原子性(如果多个线程同时访问这个变量的时候进行修改的时候会造成线程安全问题)
 * 也可以理解不能保证线程同步
 *
 * @author zoulinjun
 * @date 2020/12/4
 */
public class VolatileTest2 {

    static class Ticket{
        volatile int count = 100000;

        public void mp(){
            try{
                System.out.println(Thread.currentThread().getName() + "获取了锁");
                for (int i = 0; i < 10000; i++) {
                    count--;
                }
            }finally {
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
