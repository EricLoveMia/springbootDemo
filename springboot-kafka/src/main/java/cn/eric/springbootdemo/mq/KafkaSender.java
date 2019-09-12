package cn.eric.springbootdemo.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: KafkaSender
 * @Description: TODO
 * @company lsj
 * @date 2019/7/24 16:27
 **/
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 发送消息到kafka
     */
    public void sendChannelMess(String channel, String message) {
        kafkaTemplate.send(channel, message);
    }
}
