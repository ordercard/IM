package com.jhq.rabbit.client;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 23:13 2019-03-17 2019
 * @Modify:
 */
public class RabbitClient {
    public static void main(String[] args) throws IOException, TimeoutException {
        Address[] addresses = new Address[]{ new Address("127.0.0.1",5672)};
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection = connectionFactory.newConnection(addresses);
        Channel channel = connection.createChannel();
        channel.basicQos(64);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("recv --message:" + new String( body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume("queue_demo",consumer);
        channel.close();
        connection.close();
    }
}
