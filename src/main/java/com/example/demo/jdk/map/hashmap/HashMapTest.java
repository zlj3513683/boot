package com.example.demo.jdk.map.hashmap;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 功能：
 * hashcode
 * 对象相等  hashcode是相等
 *
 * @author zoulinjun
 * @date 2020/11/30
 */
public class HashMapTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap.put("aaa","bbb");
        hashMap.put("aaa","vvv");
        System.out.println(hashMap);

        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("111","");



        System.out.println("1:" + hashMap.hashCode());

        hashMap.put("ccc","bbb");

        System.out.println("2:" + hashMap.hashCode());

        /*MapSon mapSon = new MapSon();
        System.out.println(new MapSon<>().hashCode());
        System.out.println(mapSon.hashCode());
        System.out.println(mapSon.getClass());
        System.out.println(MapSon.class);
        Class clazz = MapSon.class;
        Class clazz1 = mapSon.getClass();

//        mapSon.dd();
        MapSon mapSon2 = (MapSon)mapSon.clone();
        System.out.println(mapSon2.hashCode());*/

    }

}
