package com.example.demo.context;

import com.example.demo.service.MessageService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能：
 *
 * @author 2020/1/13
 * @author zoulinjun
 */
@Component
public class MessageServiceContext {
    private final Map<Integer, MessageService> handlerMap = new HashMap<>();

    public MessageService getMessageService(Integer type) {
        return handlerMap.get(type);
    }

    public void putMessageService(Integer code, MessageService messageService) {
        handlerMap.put(code, messageService);
    }
}
