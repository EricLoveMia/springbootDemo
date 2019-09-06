package cn.eric.springbootdemo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: CacheInitRunner
 * @Description: TODO
 * @company lsj
 * @date 2019/5/29 16:57
 **/
@Component
@Order(1)
public class CacheInitRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("load cache..." + Arrays.asList(args));
    }
}
