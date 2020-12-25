package com.example.demo.jdk.thread;

/**
 * 功能：线程中断方法
 * interrupt()方法用于中断一个线程，而Java中线程的处理是协作式的而不是抢占式的。
 * 所谓协作式的意思是：实际上调用一个线程的interrupt() 方法中断一个线程，并不是强行关闭这个线程，只是跟这个线程打个招呼，
 * 将线程的中断标志位置为true，线程是否中断，由线程本身决定。
 *
 * @author zoulinjun
 * @date 2020/12/7
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {//判断当前线程是否被中断
                    System.out.println(Thread.currentThread().getName()+"is running,interrupted:" + Thread.currentThread().isInterrupted());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                while (true){
//                    System.out.println(Thread.currentThread().getName()+"is running,interrupted:" + Thread.currentThread().isInterrupted());
//                    if(Thread.currentThread().isInterrupted()){
//                        System.out.println("----------------------------------------------------------------------------------------------");
//                        Thread.interrupted();
//                    }
//                }
            }
        });

        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

}
