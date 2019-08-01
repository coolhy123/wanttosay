package com.hydu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import util.IdWorker;
import util.JwtUtil;

/**
 * Created by heyong
 * 2019/8/1
 */

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class FriendApp {
    public static void main(String[] args) {
        SpringApplication.run(FriendApp.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);

    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
