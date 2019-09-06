package cn.eric.springbootdemo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: CacheInitRunner
 * @Description: 平常开发中有可能需要实现在项目启动后执行的功能，SpringBoot提供的一种简单的实现方案就是添加一个model并实现CommandLineRunner接口，
 *               实现功能的代码放在实现的run方法中
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
