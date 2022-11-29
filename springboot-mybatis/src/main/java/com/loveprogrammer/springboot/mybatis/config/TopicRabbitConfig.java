package com.loveprogrammer.springboot.mybatis.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: TopicRabbitConfig
 * @Description: TODO
 * @company lsj
 * @date 2019/5/14 16:50
 **/
@Configuration
public class TopicRabbitConfig {

    final static String QUEUE_NAME = "test2-queue";

    @Bean
    public Queue test2Queue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("my_exchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("my_routingkey");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("test2.#");
    }

}
