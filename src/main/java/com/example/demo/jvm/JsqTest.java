package com.example.demo.jvm;

/**
 * 功能：解释器三种模式（一般都是选择混合方式，因为模板解释器的性能更强，但是具体业务中有的代码仅仅执行一次，
 * 用模板解释器会把代码编译放入方法区占用内存不好，则选取使用混合型）
 *  -Xint 字节码解释器    32040ms
 *  -Xcomp 模板解释器     16509ms
 *  -Xmixed   字节码模板混合解释   26945ms
 * @author zoulinjun
 * @date 2020/11/27
 */
public class JsqTest {

    public static void main(String[] args) {

        long tome1 = System.currentTimeMillis();

        print();

        long t2 = System.currentTimeMillis();

        System.out.println("耗时：" + (t2- tome1) + "ms");


    }

    private static void print(){

        for (int i = 0; i < 1000*1000; i++) {
            System.out.println("hello");
        }

    }

}
