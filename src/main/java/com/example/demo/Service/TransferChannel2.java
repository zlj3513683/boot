package com.example.demo.Service;

import org.springframework.stereotype.Service;

/**
 * 功能：
 *
 * @author 2020/1/21
 * @author zoulinjun
 */
@Service("transferChannel2")
public class TransferChannel2 implements TransferChannel {
    @Override
    public void doSomthing() {
        System.out.println("进来了TransferChannel2，图片处理");
    }
}
