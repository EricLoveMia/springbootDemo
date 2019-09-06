package cn.eric.springbootdemo.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: UserOrderListener
 * @Description: TODO
 * @company lsj
 * @date 2019/7/16 14:23
 **/
@Component("userOrderLinster")
public class UserOrderListener implements ChannelAwareMessageListener {

    AtomicInteger num = new AtomicInteger(100);

    AtomicInteger count = new AtomicInteger(0);
    AtomicInteger total = new AtomicInteger(0);
    private static final Logger log = LoggerFactory.getLogger(UserOrderListener.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            total.incrementAndGet();
            byte[] body = message.getBody();
            String mobile = new String(body,"UTF-8");
            log.info("监听到抢单手机号：{}",mobile);
            if(num.decrementAndGet() >= 0){
                //System.out.printf("抢单成功");
                count.addAndGet(1);
                System.out.println("共计" + count  + "单");
            }else{
                //System.out.printf("抢单失败");
            }
            System.out.println("共计来到" + total);
            channel.basicAck(deliveryTag,true);
        } catch (Exception e) {
            log.info("用户抢单发生异常：",e.fillInStackTrace());
            channel.basicAck(deliveryTag,false);
        }
    }

    @Override
    public void onMessage(Message message) {

    }
}
