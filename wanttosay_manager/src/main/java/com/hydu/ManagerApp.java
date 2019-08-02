package com.hydu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by heyong
 * 2019/8/2
 */

@EnableZuulProxy
@SpringBootApplication
public class ManagerApp {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApp.class,args);
    }
}
