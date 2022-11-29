package cn.eric.thrift;

import cn.eric.thrift.service.thrift.RPCThriftServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ThriftApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThriftApplication.class, args);
    }

    /**
     * 向Spring注册一个Bean对象
     * initMethod = "start"  表示会执行名为start的方法
     * start方法执行之后，就会阻塞接受客户端的请求
     *
     * @return
     */
    @Bean(initMethod = "start")
    public RPCThriftServer init() {
        RPCThriftServer thriftServer = new RPCThriftServer();
        return thriftServer;
    }
}
