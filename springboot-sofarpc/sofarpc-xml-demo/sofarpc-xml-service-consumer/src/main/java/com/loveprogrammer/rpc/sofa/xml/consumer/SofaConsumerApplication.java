package com.loveprogrammer.rpc.sofa.xml.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:sofarpc.xml")
public class SofaConsumerApplication {

    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        SpringApplication.run(SofaConsumerApplication.class, args);;
    }

}
