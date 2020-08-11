package com.example.demo.service;

import java.util.Map;

/**
 * 功能：
 *
 * @author 2020/1/21
 * @author zoulinjun
 */
public interface TransferRouter {

    TransferChannel findChannelByName(String chnName);


    /**
     * 获取具体的渠道
     * @return
     */
    Map<String,TransferChannel> getChannels();

}
