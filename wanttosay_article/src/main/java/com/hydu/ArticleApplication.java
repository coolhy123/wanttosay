package com.hydu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * Created by heyong
 * 2019/7/1
 */

@SpringBootApplication
@EnableEurekaClient
public class ArticleApplication {
    public static void main(String[] args) {

        SpringApplication.run(ArticleApplication.class,args);
    }


    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
