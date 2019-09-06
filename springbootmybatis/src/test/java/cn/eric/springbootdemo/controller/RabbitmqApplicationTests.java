package cn.eric.springbootdemo.controller;

import cn.eric.springbootdemo.mq.Producer;
import cn.eric.springbootdemo.mq.Producer2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: RabbitmqApplicationTests
 * @Description: TODO
 * @company lsj
 * @date 2019/5/13 14:44
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Autowired
    @Qualifier("producer")
    private Producer producer;

    @Autowired
    @Qualifier("producer2")
    private Producer2 producer2;

    @Test
    public void contextLoads(){

    }

    @Test
    public void sendMsg() throws Exception{
        producer.send();
        Thread.sleep(2000);
    }

    @Test
    public void sendObjectMsg() throws Exception{
        producer.sendUser();
        Thread.sleep(2000);
    }

    @Test
    public void sendMsgTimes() throws Exception{
        producer.sendTimes(20);
        producer2.sendTimes(30);
        Thread.sleep(2000);
    }

    @Test
    public void sendRouting() throws Exception{
        producer.sendRouting();
        Thread.sleep(2000);
    }
}
