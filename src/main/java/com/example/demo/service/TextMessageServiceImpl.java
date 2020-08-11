package com.example.demo.service;

import com.example.demo.bean.MessageInfo;
import com.example.demo.enums.MsgType;
import com.example.demo.handle.MsgTypeHandler;
import org.springframework.stereotype.Service;

/**
 * 功能：
 *
 * @author 2020/1/13
 * @author zoulinjun
 */
@Service
@MsgTypeHandler(value = MsgType.TEXT)
public class TextMessageServiceImpl implements MessageService{
    @Override
    public void handleMessage(MessageInfo messageInfo) {
        System.out.println("处理文本消息 " + messageInfo.getContent());
    }
}
