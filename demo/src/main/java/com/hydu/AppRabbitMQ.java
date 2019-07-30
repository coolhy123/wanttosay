package com.hydu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by heyong
 * 2019/7/19
 */

@SpringBootApplication

public class AppRabbitMQ {
    public static void main(String[] args) {
        SpringApplication.run(AppRabbitMQ.class,args);
    }

}
