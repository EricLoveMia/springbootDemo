package com.loveprogrammer.springboot.mybatis.mq;

import com.loveprogrammer.springboot.mybatis.domain.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: Producer
 * @Description: TODO
 * @company lsj
 * @date 2019/5/13 13:32
 **/
@Component
public class Producer2 {

    @Autowired
    private AmqpTemplate rabbitTemplet;

    public void send() {
        String msg = "msg" + new Date();
        System.out.println("Producer2: " + msg);
        rabbitTemplet.convertAndSend("test-queue", msg);
    }

    public void sendUser() {
        User user = new User(1L, "eric", "lovemia", "qianxu", false);
        rabbitTemplet.convertAndSend("test-queue", "msg:" + user);
    }

    public void sendTimes(int time) {
        for (int i = 0; i < time; i++) {
            String msg = "msg:" + i;
            System.out.println("Producer: " + msg);
            rabbitTemplet.convertAndSend("test-queue", msg);
        }
    }
}
