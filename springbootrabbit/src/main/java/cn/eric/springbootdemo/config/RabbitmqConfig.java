package cn.eric.springbootdemo.config;

import cn.eric.springbootdemo.mq.UserOrderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: RabbitmqConfig
 * @Description: TODO
 * @company lsj
 * @date 2019/7/15 17:31
 **/
@Configuration
public class RabbitmqConfig {
    private static final Logger log = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Autowired
    private Environment env;

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    @Autowired
    private UserOrderListener userOrderListener;

    /**
     * 单一消费者
     *
     * @return
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        factory.setTxSize(1);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    /**
     * 多个消费者
     *
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory, connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.concurrency", int.class));
        factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.max-concurrency", int.class));
        factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.prefetch", int.class));
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
            }
        });
        return rabbitTemplate;
    }

    @Bean
    public Queue logUserQueue() {
        return new Queue(env.getProperty("log.user.queue.name"), true);
    }

    @Bean
    public DirectExchange logUserExchange() {
        return new DirectExchange(env.getProperty("log.user.exchange.name"), true, false);
    }

    @Bean
    public Binding logUserBinding() {
        return BindingBuilder.bind(logUserQueue()).to(logUserExchange()).with(env.getProperty("log.user.routing.key.name"));
    }

    @Bean(name = "userOrderQueue")
    public Queue userOrderQueue() {
        return new Queue(env.getProperty("user.order.queue.name"), true);
    }

    @Bean
    public TopicExchange userOrderExchage() {
        return new TopicExchange(env.getProperty("user.order.exchange.name"), true, false);
    }

    @Bean
    public Binding userOrderBinding() {
        return BindingBuilder.bind(userOrderQueue()).to(userOrderExchage()).with(env.getProperty("user.order.routing.key.name"));
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainerUserOrder(@Qualifier("userOrderQueue") Queue userOrderQueue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageConverter(new Jackson2JsonMessageConverter());

        // 并发配置
        container.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.concurrency", Integer.class));
        container.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.max-concurrency", Integer.class));
        container.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.simple.prefetch", Integer.class));

        // 消息确认机制
        container.setQueues(userOrderQueue);
        container.setMessageListener(userOrderListener);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        return container;
    }

    @Bean(name = "registerDelayQueue")
    public Queue registerDelayQueue() {
        Map<String, Object> params = new HashMap<>();
        params.put("x-dead-letter-exchange", env.getProperty("register.exchange.name"));
        params.put("x-dead-letter-routing-key", "all");
        return new Queue(env.getProperty("register.delay.queue.name"), true, false, false, params);
    }

    @Bean(name = "registerDelayExchange")
    public DirectExchange registerDelayExchange() {
        return new DirectExchange(env.getProperty("register.delay.exchange.name"));
    }

    @Bean
    public Binding registerDelayBinding() {
        return BindingBuilder.bind(registerDelayQueue()).to(registerDelayExchange()).with("");
    }

    /**
     * 指标消费队列配置
     **/

    @Bean(name = "registerTopicExchange")
    public TopicExchange registerTopicExchange() {
        return new TopicExchange(env.getProperty("register.exchange.name"));
    }

    @Bean
    public Binding registerBinding() {
        return BindingBuilder.bind(registerQueue()).to(registerTopicExchange()).with("all");
    }

    @Bean(name = "registerQueue")
    public Queue registerQueue() {
        return new Queue(env.getProperty("register.queue.name"), true);
    }

}
