package com.example.demo.jdk.object;

import java.util.Map;

/**
 * 功能：
 *reflexive，自反性。任何非空引用值x，对于x.equals(x)必须返回true
 * symmetric，对称性。任何非空引用值x和y，如果x.equals(y)为true，那么y.equals(x)也必须为true
 * transitive，传递性。任何非空引用值x、y和z，如果x.equals(y)为true并且y.equals(z)为true，那么x.equals(z)也必定为true
 * consistent，一致性。任何非空引用值x和y，多次调用 x.equals(y) 始终返回 true 或始终返回 false，
 * 前提是对象上 equals 比较中所用的信息没有被修改
 * 对于任何非空引用值 x，x.equals(null) 都应返回 false
 * @author zoulinjun
 * @date 2020/12/1
 */
public class EquestTest {


    static class Inner implements Cloneable{
        private String s;

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Inner i1 = new Inner();
        i1.setS("二狗子");
        Inner i2 = i1;
        System.out.println(i1.equals(i2));
        Inner i3 = (Inner) i1.clone();
        System.out.println(i1.equals(i3));
        System.out.println(i3.getS());

        System.out.println(i1.toString());


        Integer it1 = 2;
        Integer it2 = 2;
        System.out.println(it1.equals(it2));
        System.out.println(it1 == it2);


        String s1 = "String1";
        String s2 = "String";
        String s3 = s2 + "1";
        System.out.println(s1 == s3);
    }

}
