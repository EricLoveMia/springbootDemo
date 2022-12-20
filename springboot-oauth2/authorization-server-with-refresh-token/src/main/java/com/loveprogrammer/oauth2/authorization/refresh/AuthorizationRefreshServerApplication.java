package com.loveprogrammer.oauth2.authorization.refresh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @version 1.0.0
 * @description: ${description}
 * @author: qianxu
 * @date: ${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}
 **/
@SpringBootApplication
public class AuthorizationRefreshServerApplication {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(AuthorizationRefreshServerApplication.class,args);
    }
}