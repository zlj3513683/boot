package com.example.demo.test;

import com.alibaba.fastjson.JSONObject;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/3/5
 */
public class TestMap {

    public static void main(String[] args) {

        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("a","aaa");
        jsonObject.put("c","ccc");
        jsonObject.put("b","bbb");
        System.out.println(jsonObject.toJSONString());
    }
}
