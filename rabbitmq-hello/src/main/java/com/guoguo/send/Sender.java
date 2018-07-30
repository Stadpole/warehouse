package com.guoguo.send;

import com.guoguo.domain.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by mengying on 2018/1/14.
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    public void send() {
        User user1 = new User();
        user1.setUsername("张三");
        user1.setPasswoed("123");
        /*String message = "hello" + new Date();
        System.out.println("Sender:" + message);*/
        this.rabbitTemplate.convertAndSend("result",user1);
    }
    public void send2() {
        User user2 = new User();
        user2.setUsername("李四");
        user2.setPasswoed("456");
        /*String message = "hello" + new Date();
        System.out.println("Sender:" + message);*/
        this.rabbitTemplate.convertAndSend("result", user2);
    }
}
