package com.example.demo.service;

import org.springframework.stereotype.Service;

/**
 * 功能：
 *
 * @author 2020/1/21
 * @author zoulinjun
 */
@Service("transferChannel1")
public class TransferChannel1 implements TransferChannel {

    @Override
    public void doSomthing() {
        System.out.println("进来了TransferChannel1,文本处理");
    }
}
