package com.hydu.custom;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by heyong
 * 2019/7/19
 */
@Component
@RabbitListener(queues="good" )
public class Costom3 {
    @RabbitHandler
    public  void showMessage(String message){
        System.out.println("good接收到消息："+message);

    }




}