package cn.eric.springbootdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: HelloController
 * @Description: TODO
 * @company lsj
 * @date 2019/4/19 18:09
 **/
@RestController
@RequestMapping("/hello")
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AmqpTemplate rabbitTemplet;


    @RequestMapping("/world")
    public String index(){

        for (int i = 0; i < 15000; i++) {
            logger.trace(i + "日志输出 trace");
            logger.debug(i + "日志输出 debug");
            logger.info(i + "日志输出 info");
            logger.warn(i + "日志输出 warn");
            logger.error(i +"日志输出 error");
        }

        return "Hello World";
    }


}
