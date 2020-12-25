package com.example.demo.jdk.map.concurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/23
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {

        ConcurrentHashMap hashMap = new ConcurrentHashMap();
        hashMap.put("111","222");
        System.out.println(hashMap.get("111"));
    }

}
