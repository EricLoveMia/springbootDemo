package cn.eric.springbootdemo.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
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
            // 如果要测试异常后重新入队 请打开下面的代码
//            if(count.get() > 40){
//                int i = 2/0;
//            }

            total.incrementAndGet();
            byte[] body = message.getBody();
            String mobile = new String(body, StandardCharsets.UTF_8);
            log.info("监听到抢单手机号：{}", mobile);
            if (num.decrementAndGet() >= 0) {
                //System.out.printf("抢单成功");
                count.addAndGet(1);
                System.out.println("共计" + count + "单");
                // BasicAck方法的第二个参数 multiple 取值为 false 时，表示通知 RabbitMQ 当前消息被确认；
                // 如果为 true，则额外将比第一个参数指定的 delivery tag 小的消息一并确认
                channel.basicAck(deliveryTag, false);
            } else {
                System.out.printf("抢单失败");
                //channel.basicNack(deliveryTag,false, true);//第三个参数为是否重返队列
                channel.basicNack(deliveryTag, false, false);
            }
            System.out.println("共计来到" + total);

        } catch (Exception e) {
            log.info("用户抢单发生异常：", e.fillInStackTrace());
            // channel.basicAck(deliveryTag,false);
            // channel.basicAck(deliveryTag,true);
            // 第一个参数指定 delivery tag，第二个参数说明如何处理这个失败消息。requeue 值为 true 表示该消息重新放回队列头，值为 false 表示放弃这条消息
            channel.basicReject(deliveryTag, true);
            // 也可以手动确认已经消费 然后手动重新放入队列中，或者将异常的数据写入数据库 然后定时扫描 再入队列 根据使用场景
        }
    }

    @Override
    public void onMessage(Message message) {

    }
}
