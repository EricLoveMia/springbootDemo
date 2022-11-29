package com.loveprogrammer.springboot.websocket.tomcat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @version 1.0.0
 * @description: 配置类
 * @author: eric
 * @date: 2022-11-29 10:23
 **/
@Configuration
public class WebSocketConfiguration {

    // 该 Bean 的作用，是扫描添加有 @ServerEndpoint 注解的 Bean
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
