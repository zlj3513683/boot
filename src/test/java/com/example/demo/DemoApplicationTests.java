package com.example.demo;

import com.example.demo.Service.MessageService;
import com.example.demo.Service.TransferChannel;
import com.example.demo.Service.TransferRouter;
import com.example.demo.bean.MessageInfo;
import com.example.demo.context.MessageServiceContext;
import com.example.demo.enums.MsgType;
import com.example.demo.enums.TransEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    MessageServiceContext messageServiceContext;
    @Autowired
    private TransferRouter transferRouter;
    ////@Test
    void contextLoads() {
        MessageInfo messageInfo = new MessageInfo(MsgType.IMAGE.code, "我是一个图片消息");
        MessageService messageService = messageServiceContext.getMessageService(messageInfo.getType());
        // 处理文本消息 消息内容
        // 可以看到文本消息被文本处理类所处理
        messageService.handleMessage(messageInfo);
    }

    ////@Test
    void transferRouter() {
       String[] typeArr = {"1","3","2"};
        for (String type:typeArr
             ) {
            String impl = TransEnum.getByCode(type).name;
            TransferChannel channel = transferRouter.findChannelByName(impl);
            channel.doSomthing();
        }
    }

}
