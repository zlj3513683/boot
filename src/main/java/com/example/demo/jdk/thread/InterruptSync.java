package com.example.demo.jdk.thread;

/**
 * 功能：测试synchronized不可中断
 *
 * @author zoulinjun
 * @date 2020/12/7
 */
public class InterruptSync {

    static class Bank{

        private volatile static int count = 0;

        public synchronized void add(){
            System.out.println(Thread.currentThread().getName());
            while(true){
                count ++ ;
            }
        }

    }


    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank();

        Thread thread1 = new Thread(bank::add,"t1");
        Thread thread2 = new Thread(bank::add,"t2");

        thread1.start();
        thread2.start();
        Thread.sleep(5000);
        thread1.interrupt();
        System.out.println("中断线程1");
    }

}
