package cn.eric.springbootdemo.service;

import cn.eric.springbootdemo.domain.Order;
import cn.eric.springbootdemo.domain.User;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: OrderService
 * @Description: TODO
 * @company lsj
 * @date 2019/7/16 17:23
 **/
@Service
public class OrderService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;

    public void createOrder(Order order) {

        System.out.println(order.toString());


        //TODO：设置超时，用mq处理已超时的下单记录（一旦记录超时，则处理为无效）
        final Long ttl = env.getProperty("trade.record.ttl", Long.class);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(env.getProperty("register.delay.exchange.name"));
        rabbitTemplate.setRoutingKey("");
        rabbitTemplate.convertAndSend(order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, User.class.getName());
                message.getMessageProperties().setExpiration(ttl + "");
                return message;
            }
        });

    }
}
