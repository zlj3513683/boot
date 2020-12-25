package com.example.demo.jdk.list;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/9
 */
public class LinkedListDemo1 { //表示整个链表对象

    private Node first;        //链表对象的第一个引用

    public LinkedListDemo1(){

    }

    //插入对象到链表首位置
    public void insertFirst(String item){
        //创建链表对象
        LinkedListDemo1 list=new LinkedListDemo1();
        //原来的首个节点暂存在：用oldFirst引用指向
        Node oldFirst=first;
        //创建需要插入的节点对象
        Node newNode=new Node();
        newNode.item=item;
        //新节点对象的next引用指向原先的首节点对象
        newNode.next=oldFirst;

    }


    public Node getFirst() {
        return first;
    }


    public void setFirst(Node first) {
        this.first = first;
    }


    class Node{              //节点对象
        String item;          //存储的数据对象
        Node next;          //下一个节点对象的引用
        public String getItem() {
            return item;
        }
        public void setItem(String item) {
            this.item = item;
        }
        public Node getNext() {
            return next;
        }
        public void setNext(Node next) {
            this.next = next;
        }


    }
}
