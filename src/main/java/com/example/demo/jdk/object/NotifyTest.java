package com.example.demo.jdk.object;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/1
 */
public class NotifyTest {


    static class Bank {
        private int count = 100;
        private int cot = 0;

        public void addMoney(int money){
            synchronized (this){
                count += money;
            }
        }

        public void subMoney(int money){
            synchronized (this){
                if(count < money){
//                System.out.println("余额不足");
                    cot ++ ;
                }else{
                    count = count - money;
                }
            }
        }

        public int lookMoney(){
            return count;
        }

    }


    public static void main(String[] args) {
//        for (int a = 0; a < 10; a++) {


        final Bank bank=new Bank();

        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int j = 0; j < 10000; j++) {
                    bank.addMoney(100);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                int count = bank.lookMoney();
                System.out.println(count + "add");
//                System.out.println(bank.cot + "余额不足次数");
            }
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int j = 0; j < 10000; j++) {
                    bank.subMoney(100);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                int count = bank.lookMoney();
                System.out.println(count);
                System.out.println(bank.cot + "余额不足次数");
            }
        }).start();




        /*Thread sub = new Thread(new Runnable(){
            @Override
            public void run() {
                while (true){
                    bank.subMoney(100);
                    bank.lookMoney();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println();
                }
            }
        });*/

        }
//        add.start();
//        sub.start();
//    }

}
