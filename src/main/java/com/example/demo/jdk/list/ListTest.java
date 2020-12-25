package com.example.demo.jdk.list;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/8
 */
public class ListTest {

    public static void main(String[] args) throws IOException {
        LinkedList linkedList = new LinkedList();
        linkedList.add("111");
        linkedList.add("222");
        System.out.println(linkedList.get(1));
        linkedList.add(1,"333");
        System.out.println(linkedList.get(1));
        linkedList.remove(1);
        System.out.println(linkedList.get(1));
        ArrayList arrayList = new ArrayList();
        arrayList.add("aaaa");
        arrayList.add(123);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        System.out.println(arrayList.get(2));;
        arrayList.add(1,"1111");
        System.out.println(arrayList.get(2));;
        String[] arr = new String[10];
        arr[0] = "2";
        arr[1] = "1";
        arr[2] = "11";
        arr[3] = "111";
        System.out.println(arr[2]);
        arr[2] = null;
        System.out.println(arr[2]);

        LinkedListDemo1 linkedListDemo1 = new LinkedListDemo1();
//        LinkedListDemo1.Node node = new LinkedListDemo1().Node();
//        linkedListDemo1.setFirst();

        System.out.println("请输入");
        System.in.read();
    }

}
