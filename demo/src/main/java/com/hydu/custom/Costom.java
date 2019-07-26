package custom;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by heyong
 * 2019/7/19
 */
@Component
@RabbitListener(queues="hydu2" )
public class Costom {
    @RabbitHandler
    public  void showMessage(String message){
        System.out.println("hydu接收到消息："+message);

    }




}