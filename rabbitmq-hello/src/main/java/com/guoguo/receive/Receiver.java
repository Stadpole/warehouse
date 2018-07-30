package com.guoguo.receive;

import com.google.gson.Gson;
import com.guoguo.domain.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by mengying on 2018/1/14.
 */
@Component
@RabbitListener(queues = "search")
public class Receiver {
    @RabbitHandler
    public void process(User user) {
        Gson gson = new Gson();
        String msg = gson.toJson(user);
        System.out.println("Receiver:" + msg);
    }
}
