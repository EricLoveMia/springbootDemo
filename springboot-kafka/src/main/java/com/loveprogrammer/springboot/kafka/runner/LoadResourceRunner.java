package com.loveprogrammer.springboot.kafka.runner;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
        System.out.println("===MyApplicationRunner===" + Arrays.asList(args.getSourceArgs()));
        System.out.println("===getOptionNames========" + args.getOptionNames());
        System.out.println("===getOptionValues=======" + args.getOptionValues("foo"));
        System.out.println("===getOptionValues========" + args.getOptionValues("developer.name"));
        System.out.println("create thread loop" + args);
    }
}
