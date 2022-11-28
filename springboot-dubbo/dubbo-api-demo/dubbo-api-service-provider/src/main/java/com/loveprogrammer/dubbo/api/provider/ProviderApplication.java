package com.loveprogrammer.dubbo.api.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @version 1.0.0
 * @description:
 * @author: eric
 * @date: 2022-11-28 11:25
 **/
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class,args);
    }
}
