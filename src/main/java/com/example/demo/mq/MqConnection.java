package com.example.demo.mq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/10/16
 */
public class MqConnection {
    public static Connection GetRabbitConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("localhost");
        factory.setPort(5672);
//        factory.setUsername(Config.UserName);
//        factory.setPassword(Config.Password);
//        factory.setVirtualHost(Config.VHost);
//        factory.setHost(Config.Host);
//        factory.setPort(Config.Port);
        Connection conn = null;
        try {
            conn = factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection GetRabbitConnection2() {
        ConnectionFactory factory = new ConnectionFactory();
        // 连接格式：amqp://userName:password@hostName:portNumber/virtualHost
        String uri = String.format("amqp://%s:%s@%s:%d%s",
                "guest", "guest",
                "localhost", 15672,
                "/");
        Connection conn = null;
        try {
            factory.setUri(uri);
            factory.setVirtualHost("/");
            conn = factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
