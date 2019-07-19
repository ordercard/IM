package com.jhq.rabbit.server;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 22:57 2019-03-17 2019
 * @Modify:
 */
public class RabbitServer  {
    public static final String  EXCHANGE_NAME = "exchange_de";
    public static final String  ROUTING_KEY = "routing_key";
    public static final String  QUEUE_NAME = "queue_demo";
    public static final Integer  PORT =5672;

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        String message ="hello";
        channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        channel.close();
        connection.close();
    }
}
