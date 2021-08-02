package cn.eric.thrift.config;

import cn.eric.thrift.service.RPCThriftClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RPCThriftClientConfig
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/8/2
 * @Version V1.0
 **/
@Configuration
public class RPCThriftClientConfig {

    @Value("${thrift.host}")
    private String host;
    @Value("${thrift.port}")
    private int port;

    @Bean(initMethod = "init")
    public RPCThriftClient rpcThriftClient() {
        RPCThriftClient rpcThriftClient = new RPCThriftClient();
        rpcThriftClient.setHost(host);
        rpcThriftClient.setPort(port);
        return rpcThriftClient;
    }

}