package com.loveprogrammer.springboot.mybatis.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: RabbitMQConfig
 * @Description: TODO
 * @company lsj
 * @date 2019/5/13 13:29
 **/
@Configuration
public class RabbitMQConfig {

//    @Bean
//    public Queue testQueue(){
//        return new Queue("test-queue");
//    }

//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(new Jackson2JsonMessageConverter());
//        return template;
//    }
//
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        return factory;
//    }
}
