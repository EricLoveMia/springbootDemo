package cn.eric.springbootdemo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: Comsumer
 * @Description: TODO
 * @company lsj
 * @date 2019/5/13 13:34
 **/
@Component
public class Comsumer3 {

    @RabbitHandler
    @RabbitListener(queues = "test2-queue")
    public void process(String msg) {
        System.out.println("Receiver3: " + msg);
    }
}
