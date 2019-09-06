package cn.eric.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/*** druid配置WebStatFilter完成网络url统计 **/
/*** mybatis **/
@SpringBootApplication
@MapperScan("cn.eric.springbootdemo.mapper")
@ServletComponentScan
public class SpringbootmybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmybatisApplication.class, args);
    }

}
