package com.example.demo.jdk.jni;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/1
 */
public class HelloWorld{

    static {

        System.load("D://jni//h//hello//x64//Debug//hello.dll");//载入dll  Connector.dll

        //D:\jni\h\hello\x64\Debug
    }
    public static void main(String args []){

        HelloWorld helloWord = new HelloWorld();
        helloWord.sayHello("nihao");

    }
    public  native void sayHello(String name);

}
