package com.example.demo.jdk.thread;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/3
 */
public class ThreaStaticTest {


    static class MyThread{

        public static int count = 0;

        public void print1(){
            System.out.println(Thread.currentThread().getName() + " start");
            try {
                System.out.println(Thread.currentThread().getName() + " exeute");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }

        public synchronized void print2(){
            System.out.println(Thread.currentThread().getName() + " start");
            try {
                System.out.println(Thread.currentThread().getName() + " exeute");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }

        public synchronized static void print3(){
            System.out.println(Thread.currentThread().getName() + " start");
            try {
                System.out.println(Thread.currentThread().getName() + " exeute");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }

        public synchronized void print4(){
            System.out.println(Thread.currentThread().getName() + " start");
            try {
                System.out.println(Thread.currentThread().getName() + " exeute");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }

        public synchronized static void add(){
            try {
                for (int i = 0; i < 10000; i++) {
                    count ++;
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized static void red(){
            try {
                for (int i = 0; i < 10000; i++) {
                    count --;
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
//       final MyThread myThread1 = new MyThread();
//       new Thread(myThread1::print1,"mythread1").start();
//       new Thread(myThread1::print4,"mythread2").start();

//       new Thread(new Runnable() {
//           @Override
//           public void run() {
//               MyThread.print3();
//           }
//       },"t3").start();
//
//       new Thread(new Runnable() {
//           @Override
//           public void run() {
//               MyThread.print3();
//           }
//       },"t4").start();

        Thread t1= new Thread(new Runnable() {
            @Override
            public void run() {
                MyThread.add();
            }
        },"t3");

        Thread t2= new Thread(new Runnable() {
            @Override
            public void run() {
                MyThread.red();
            }
        },"t4");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(MyThread.count);
    }

}
