package com.example.demo.jdk.thread;

/**
 * 功能：volatile貌似不能实现同步效果
 *
 * @author zoulinjun
 * @date 2020/12/3
 */
public class VoliatorThread {

    static class Bank{
        private volatile int count = 0;

        public void add(){
            for (int i = 0; i < 100000; i++) {
                count++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Bank bank= new Bank();
        Thread t1 = new Thread(bank::add);
        Thread t2 = new Thread(bank::add);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(bank.count);
    }

}
