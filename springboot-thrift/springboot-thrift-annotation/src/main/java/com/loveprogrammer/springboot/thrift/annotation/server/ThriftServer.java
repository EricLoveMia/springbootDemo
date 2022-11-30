package com.loveprogrammer.springboot.thrift.annotation.server;

import com.facebook.nifty.core.NettyServerConfig;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftEventHandler;
import com.facebook.swift.service.ThriftServiceProcessor;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThriftServer
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/8/12
 * @Version V1.0
 **/
public class ThriftServer {

    public static void main(String[] args) throws Exception {

        ThriftServiceProcessor processor = new ThriftServiceProcessor(
                new ThriftCodecManager(),
                new ArrayList<ThriftEventHandler>(),
                new HelloServiceImpl()
        );

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        ThriftServerDef serverDef = ThriftServerDef.newBuilder()
                .listen(8899)
                .withProcessor(processor)
                .using(executorService)
                .build();

        ExecutorService bossExecutor = Executors.newCachedThreadPool();
        ExecutorService workerExecutor = Executors.newCachedThreadPool();
        NettyServerConfig serverConfig = NettyServerConfig.newBuilder()
                .setBossThreadExecutor(bossExecutor)
                .setWorkerThreadExecutor(workerExecutor)
                .build();

        com.facebook.swift.service.ThriftServer server = new com.facebook.swift.service.ThriftServer(serverConfig, serverDef);
        server.start();
    }
}
