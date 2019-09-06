package cn.eric.springbootdemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: CommonMqService
 * @Description: TODO
 * @company lsj
 * @date 2019/7/16 14:52
 **/
@Service
public class CommonMqService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment env;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendRobbingMsgV2(String mobile){

        rabbitTemplate.setExchange(env.getProperty("user.order.exchange.name"));
        rabbitTemplate.setRoutingKey(env.getProperty("user.order.routing.key.name"));

        try {
            Message build = MessageBuilder.withBody(mobile.getBytes("UTF-8")).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
            rabbitTemplate.send(build);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
