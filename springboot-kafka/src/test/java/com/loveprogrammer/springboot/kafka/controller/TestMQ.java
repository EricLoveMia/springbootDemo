package com.loveprogrammer.springboot.kafka.controller;

import com.loveprogrammer.springboot.kafka.mq.KafkaSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: TestMQ
 * @Description: TODO
 * @company lsj
 * @date 2019/7/24 16:28
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMQ {

    @Autowired
    KafkaSender kafkaSender;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSendMessage() throws InterruptedException {
        kafkaSender.sendChannelMess("dblab01", "hello2");
        kafkaSender.sendChannelMess("dblab01", "hello3");
        Thread.sleep(2000);
    }


}
