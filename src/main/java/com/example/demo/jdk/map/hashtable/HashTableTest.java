package com.example.demo.jdk.map.hashtable;

import java.util.Hashtable;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/23
 */
public class HashTableTest {

    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("ergouzi","而走低");
        System.out.println(hashtable.get("ergouzi"));
    }

}
