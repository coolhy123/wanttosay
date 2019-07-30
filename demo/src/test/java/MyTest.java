import com.hydu.AppRabbitMQ;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by heyong
 * 2019/7/19
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppRabbitMQ.class)//指定程序入口
public class MyTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testSend(){
        //直接模式
        rabbitTemplate.convertAndSend("hydu2","测试RabbitMQ");



    }
    //分裂模式

    @Test
    public void testSend2(){
        //直接模式
        rabbitTemplate.convertAndSend("test","","分裂模式");


    }

    @Test
    public void testSend3(){
        //直接模式
        rabbitTemplate.convertAndSend("topicTest","good.log","topic模式");


    }
}