package com.example.demo.jdk.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能：synchronized同步普通方法、同步静态方法、同步代码块、用ReentrantLock实现同步
 *
 * @author zoulinjun
 * @date 2020/12/1
 */
public class ThreadTest {


    static class Bank{

        public static int count = 0;

        public synchronized void add(int money){
//            synchronized(this){
//                count += money;
//            }

            for (int i = 0; i < 1000; i++) {
                count += money;
            }
        }

        public synchronized void reduce(int money){
//            if(count < money){
//                return;
//            }
//            synchronized(this){
//                count = count - money;
//            }

            for (int i = 0; i < 1000; i++) {
                count = count - money;
            }
        }

        public synchronized static void add4(int money){
//            synchronized(this){
//                count += money;
//            }

            for (int i = 0; i < 1000; i++) {
                count += money;
            }
        }

        public synchronized static void reduce4(int money){
//            if(count < money){
//                return;
//            }
//            synchronized(this){
//                count = count - money;
//            }

            for (int i = 0; i < 1000; i++) {
                count = count - money;
            }
        }

        public  void add2(int money){
            synchronized(this){
                for (int i = 0; i < 1000; i++) {
                    count += money;
                }
            }
        }

        public  void reduce2(int money){
//            if(count < money){
//                return;
//            }
            synchronized(this){
                for (int i = 0; i < 1000; i++) {
                    count = count - money;
                }
            }
        }

        ReentrantLock lock = new ReentrantLock();

        // 方法三：使用ReentrantLock，更加灵活，可以加入判断，在特定的情况下释放锁
        private void add3(int money) {
            lock.lock();
            try {
                for (int i = 0; i < 1000; i++) {
                    count += money;
                }
                // 保证锁一定会被释放，不会出现死锁情况
            } finally {
                lock.unlock();// 释放锁
            }
        }

        private void reduce3(int money) {
            lock.lock();
            try {
                for (int i = 0; i < 1000; i++) {
                    count = count - money;
                }
                // 保证锁一定会被释放，不会出现死锁情况
            } finally {
                lock.unlock();// 释放锁
            }
        }

        public int lookMoney(){
//            System.out.println(count);
            return count;
        }

    }


    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();


        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1; j++) {
                        bank.add(1);
//                        System.out.println("add余额：" + bank.lookMoney());
//                        if(bank.lookMoney() == 100000){
//                            System.out.println("金额正确");
//                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
//                    System.out.println(Thread.currentThread().getName() + ";add余额：" + bank.lookMoney());
                }
            },"thredadd" + i));
        }

        List<Thread> list2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list2.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1; j++) {
                        bank.reduce(1);
//                        System.out.println("reduce余额：" + bank.lookMoney());
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            },"thredredce" + i));
        }

        for (Thread t:
             list) {
            t.start();
        }
        for (Thread t:
             list2) {
            t.start();
        }

        for (Thread t:
                list) {
            t.join();
        }
        for (Thread t:
                list2) {
            t.join();
        }



        System.out.println(bank.lookMoney());

//        new Thread(bank::lookMoney).start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    bank.add(100);
                    System.out.println(Thread.currentThread().getName() + ":" + bank.lookMoney());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    bank.reduce(100);
                    System.out.println(Thread.currentThread().getName() + ":" + bank.lookMoney());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/

        /*Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    bank.add(1);
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
                System.out.println(Thread.currentThread().getName() + ":" + bank.lookMoney());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    bank.reduce(1);
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
                System.out.println(Thread.currentThread().getName() + ":" + bank.lookMoney());
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    bank.reduce(1);
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
                System.out.println(Thread.currentThread().getName() + ":" + bank.lookMoney());
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    bank.add(1);
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
                System.out.println(Thread.currentThread().getName() + ":" + bank.lookMoney());
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();*/
    }


}
