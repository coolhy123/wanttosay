package com.hydu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * Created by heyong
 * 2019/7/4
 */

@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {

        SpringApplication.run(UserApplication.class,args);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
