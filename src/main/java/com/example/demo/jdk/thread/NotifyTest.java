package com.example.demo.jdk.thread;

/**
 * 功能：notify和notifyAll
 *notify:唤醒一个在此对象监视器上等待的线程(监视器相当于就是锁的概念)。如果所有的线程都在此对象上等待，那么只会选择一个线程。选择是任意性的，并在对实现做出决定时发生。一个线程在对象监视器上等待可以调用wait方法。
 * notifyAll：跟notify一样，唯一的区别就是会唤醒在此对象监视器上等待的所有线程，而不是一个线程。
 * @author zoulinjun
 * @date 2020/12/7
 */
public class NotifyTest {

    static class Bank{

        public synchronized void add()  {
            System.out.println(Thread.currentThread().getName() + "开始等待了");
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "开始执行了");
            while (true){

            }
        }

        public synchronized void unlock(){
            System.out.println("hh");
            notify();
        }

        public synchronized void unlocks(){
            System.out.println("hh");
            notifyAll();
        }

    }

    public static void main(String[] args) throws InterruptedException {


        Bank bank = new Bank();


        new Thread(bank::add).start();
        new Thread(bank::add).start();
        new Thread(bank::add).start();
        new Thread(bank::add).start();


        System.out.println(111);
        Thread.sleep(3000);
//        new Thread(bank::unlock).start();
//        new Thread(bank::unlocks).start();

    }

}
