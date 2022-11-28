package com.loveprogrammer.springboot.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/world")
    public String index() {
        for (int i = 0; i < 15000; i++) {
            logger.trace(i + "日志输出 trace");
            logger.debug(i + "日志输出 debug");
            logger.info(i + "日志输出 info");
            logger.warn(i + "日志输出 warn");
            logger.error(i + "日志输出 error");
        }

        return "Hello World";
    }


}
