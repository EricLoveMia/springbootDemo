package com.loveprogrammer.springboot.rpc.server;

import com.loveprogrammer.springboot.rpc.inter.HelloService;

/**
 * @ClassName HelloServiceImpl
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/8/13
 * @Version V1.0
 **/

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello," + name;
    }
}
