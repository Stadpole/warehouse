package com.bupt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class XyApplication {
    public static void main(String[] args) {
        SpringApplication.run(XyApplication.class, args);
    }
}