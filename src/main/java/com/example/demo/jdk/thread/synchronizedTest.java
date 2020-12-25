package com.example.demo.jdk.thread;

/**
 *
 * @Description: 不能实现 等待锁的时候，5秒没有获取到锁，中断等待，线程继续做其它事情。
 * 参考：http://hi.baidu.com/cyberniuniu/item/7fdba2fbe9373b733d198b34
 * @author thrillerzw
 * @version 1.0
 * @create time 2014-4-25 下午12:52:02
 * 输出：
 *   开始往这个buff写入数据…
不等了，尝试中断
中断结束
 */
public class synchronizedTest {
    public static void main(String[] args) throws InterruptedException {

        Buffer buff = new Buffer();
        final Writer writer = new Writer(buff);
        final Reader reader = new Reader(buff);

        writer.start();
        Thread.sleep(1000);
        reader.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                long start = System.currentTimeMillis();
                for (;;) {
                    //等5秒钟去中断读
                    if (System.currentTimeMillis()
                            - start > 5000) {
                        System.out.println("不等了，尝试中断");
                        reader.interrupt();
                        break;
                    }

                }
                System.out.println("中断结束");

            }
        }).start();

    }
    public static class Writer extends Thread {

        private Buffer buff;

        public Writer(Buffer buff) {
            this.buff = buff;
        }

        @Override
        public void run() {
            buff.write();
            System.out.println("写结束");
        }

    }
    public static class Reader extends Thread {

        private Buffer buff;

        public Reader(Buffer buff) {
            this.buff = buff;
        }

        @Override
        public void run() {

            buff.read();//一直阻塞

            System.out.println("读结束");

        }

    }
    public static class Buffer {

        private Object lock;

        public Buffer() {
            lock = this;
        }

        public void write() {
            synchronized (lock) {
                long startTime = System.currentTimeMillis();
                System.out.println("开始往这个buff写入数据…");
                for (;;)// 模拟要处理很长时间
                {
                    if (System.currentTimeMillis()
                            - startTime > Integer.MAX_VALUE)
                        break;
                }
                System.out.println("终于写完了");
            }
        }

        public void read() {
            synchronized (lock) { //一直阻塞
                System.out.println("从这个buff读数据");
            }
        }
    }
}