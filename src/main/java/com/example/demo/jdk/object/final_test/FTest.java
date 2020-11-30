package com.example.demo.jdk.object.final_test;

import com.example.demo.jdk.object.Test;

/**
 * 功能：final: Final用于修饰类、成员变量和成员方法。
 * final修饰的类，不能被继承（String、StringBuilder、StringBuffer、Math，不可变类），其中所有的方法都不能被重写
 * (这里需要注意的是不能被重写，但是可以被重载，这里很多人会弄混)，所以不能同时用abstract和final修饰类
 * （abstract修饰的类是抽象类，抽象类是用于被子类继承的，和final起相反的作用）；Final修饰的方法不能被重写，
 * 但是子类可以用父类中final修饰的方法；Final修饰的成员变量是不可变的，如果成员变量是基本数据类型，
 * 初始化之后成员变量的值不能被改变，如果成员变量是引用类型，那么它只能指向初始化时指向的那个对象，不能再指向别的对象，
 * 但是对象当中的内容是允许改变的。
 *
 * @author zoulinjun
 * @date 2020/11/30
 */
public class FTest {

    public static void main(String[] args) {
        //1.final修饰类 FinalTest：无法被继承 方法不能重写 不能同时用abstract和final修饰类

        //2.finale修饰成员变量 变量不可变  也就是基础类型的值不变和引用类型的引用不可变
        ParmFinalTest parmFinalTest = new ParmFinalTest();
//        parmFinalTest.i1 = 2;  报错

        Integer i2 = parmFinalTest.i2;
        System.out.println(i2.toString());
        System.out.println(parmFinalTest.i2.toString());
//        i2 = new Integer(222);
//        System.out.println(i2.hashCode());
//        System.out.println(parmFinalTest.i2);
//        System.out.println(i2.toString());
        System.out.println();

        System.out.println(parmFinalTest.s);
        String ss = parmFinalTest.s;
        ss = "231122222222";
        System.out.println(parmFinalTest.s);
        System.out.println();

        Test r1 = parmFinalTest.f1;
        System.out.println(r1.toString());
        System.out.println(parmFinalTest.f1.toString());
        r1.setS("222222");
        System.out.println(r1.getS());
        r1.setS("333333");
        System.out.println(r1.getS());
        r1 = new Test();
        System.out.println(r1.getS());
        System.out.println(r1.toString());
        parmFinalTest.f1.setS("23112");
        System.out.println(parmFinalTest.f1.toString());
        //3、final修饰成员方法  方法不能被重写
        System.out.println(r1.equals(parmFinalTest.f1));


        System.out.println();
        String a = "aaaa";
        String b = a;
        testClass(a);
//        testClass(b);
        System.out.println(a);
        System.out.println(b);
        //a  b ----"aaaa"
        //
        Test t = new Test();
        t.setS("1111");
        testClass(t);
        System.out.println(t.getS());

        System.out.println("-----------------------");
        String a1 = "string1";
        final String b1 = "string";
        String d = "string";
        String c = b1 + 1;
        String e = d + 1;
        System.out.println((a1 == c));
        System.out.println((a1 == e));
        System.out.println(a1.equals(e));

        String aaa = "222";
        String bbb = "22";
        System.out.println(aaa == (bbb + 2));


    }


    public static void testClass(Object object){

        if(object instanceof String){
            object = "2465";
        }
        if(object instanceof Test){
            object = new Test();
            ((Test) object).setS("222");
        }


    }


}
