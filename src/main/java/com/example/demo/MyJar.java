package com.example.demo;

import org.springframework.stereotype.Component;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/8/12
 */
@Component
public class MyJar {

    public String myJarToString(String string){
        return "这是我的jar包~你刚才输入的是："+string;
    }

}
