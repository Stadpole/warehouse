package com.guoguo.config;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by mengying on 2018/1/14.
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue testQueue() {
        return new Queue("result");
    }
}
