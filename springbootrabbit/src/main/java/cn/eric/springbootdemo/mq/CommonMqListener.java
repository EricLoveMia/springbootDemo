package cn.eric.springbootdemo.mq;

import cn.eric.springbootdemo.domain.Order;
import cn.eric.springbootdemo.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: CommonMqListener
 * @Description: TODO
 * @company lsj
 * @date 2019/7/16 13:59
 **/
@Component
public class CommonMqListener {

    private static final Logger log = LoggerFactory.getLogger(CommonMqListener.class);

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 监听消费用户日志
     *
     * @param message
     */
    @RabbitListener(queues = "${log.user.queue.name}", containerFactory = "singleListenerContainer")
    public void consumeUserLogQueue(@Payload byte[] message) {
        try {
            User userLog = objectMapper.readValue(message, User.class);
            log.info("监听消费用户日志 监听到消息： {} ", userLog);
            //TODO：记录日志入数据表
            System.out.println(userLog.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //直接消费模式
    @RabbitListener(queues = "${register.queue.name}", containerFactory = "singleListenerContainer")
    public void consumeMessage(@Payload Order record) {
        try {
            log.debug("消费者监听交易记录信息： {} ", record);

            //TODO：表示已经到ttl了，却还没付款，则需要处理为失效
            if (Objects.equals(1, record.getStatus())) {
                record.setStatus(0);
                System.out.println("将订单状态改为失效" + record.toString());
                // orderTradeRecordMapper.updateByPrimaryKeySelective(record);
            }
        } catch (Exception e) {
            log.error("消息体解析 发生异常； ", e.fillInStackTrace());
        }
    }

}