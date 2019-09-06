package cn.eric.springbootsharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "cn.eric.springbootsharding.mapper")
@SpringBootApplication
public class SpringbootShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShardingApplication.class, args);
    }

}
