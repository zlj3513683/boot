package com.example.demo.mq;
import com.rabbitmq.client.*;

import java.util.Date;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/10/16
 */
public class Producer {
    private final static String QUEUE_NAME = "hello";
    /**
     * 推送消息
     */
    public static void Publisher() {
        // 创建一个连接
        Connection conn = MqConnection.GetRabbitConnection();
        if (conn != null) {
            try {
                // 创建通道
                Channel channel = conn.createChannel();
                // 声明队列【参数说明：参数一：队列名称，参数二：是否持久化；参数三：是否独占模式；参数四：消费者断开连接时是否删除队列；参数五：消息其他参数】
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String content = String.format("二狗子来消息了，当前时间：%s", System.currentTimeMillis());
                // 发送内容【参数说明：参数一：交换机名称；参数二：队列名称，参数三：消息的其他属性-routing headers，此属性为MessageProperties.PERSISTENT_TEXT_PLAIN用于设置纯文本消息存储到硬盘；参数四：消息主体】
                channel.basicPublish("", QUEUE_NAME, null, content.getBytes("UTF-8"));
                System.out.println("已发送消息：" + content);
                // 关闭连接
                channel.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
