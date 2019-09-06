package cn.eric.springbootdemo.mq;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: KafkaConsumer
 * @Description: TODO
 * @company lsj
 * @date 2019/7/24 16:27
 **/
@Component
public class KafkaConsumer {


    /**
     * 监听seckill主题,有消息就读取
     * @param message
     */
    @KafkaListener(topics = {"dblab01"})
    public void receiveMessage(String message){
        //收到通道的消息之后执行秒杀操作

        System.out.println(message);
    }
}
