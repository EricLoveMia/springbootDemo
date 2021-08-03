package cn.eric.springbootdemo.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: LoadResourceRunner
 * @Description: TODO
 * @company lsj
 * @date 2019/5/29 16:59
 **/
@Component
@Order(2)
public class LoadResourceRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("create thread loop" + args);
    }
}
