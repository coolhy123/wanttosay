package com.hydu.custom;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by heyong
 * 2019/7/19
 */
@Component
@RabbitListener(queues="hydu" )
public class Costom2 {
    @RabbitHandler
    public  void showMessage(Map<String,Object> map){
        System.out.println("hydu接收到消息："+map.get("mobile")+":"+map.get("checkcode"));

    }




}