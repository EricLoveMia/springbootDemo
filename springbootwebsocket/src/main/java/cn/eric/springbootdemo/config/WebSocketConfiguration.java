package cn.eric.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: WebSocketConfiguration
 * @Description: 开启WebSocket支持
 * @company lsj
 * @date 2019/9/12 16:53
 **/
@Configuration
public class WebSocketConfiguration {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
