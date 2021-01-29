package com.example.demo.test;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.AppDemoController;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.util.Calendar;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/3/5
 */
public class TestMap {

    public static void main(String[] args) {
        System.out.println("666100000000888".length());
        System.out.println("10000181".length());

        Calendar calendar11=Calendar.getInstance();
        calendar11.add(Calendar.DATE,-11);
        System.out.println(calendar11.getTime());
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("a","aaa");
        jsonObject.put("c","ccc");
        jsonObject.put("b","bbb");
        System.out.println(jsonObject.toJSONString());
    }
}
