package com.example.demo.Service;

import org.springframework.stereotype.Service;

/**
 * 功能：
 *
 * @author 2020/1/21
 * @author zoulinjun
 */
@Service("transferChannel3")
public class TransferChannel3 implements TransferChannel {
    @Override
    public void doSomthing() {
        System.out.println("进来了TransferChannel3，视频处理");
    }
}
