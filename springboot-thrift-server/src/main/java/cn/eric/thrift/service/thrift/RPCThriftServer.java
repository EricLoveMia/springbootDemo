package cn.eric.thrift.service.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName RPCThriftServer
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/7/28
 * @Version V1.0
 **/
@Component
public class RPCThriftServer {
    private final Logger logger = LoggerFactory.getLogger(RPCThriftServer.class);

//    @Value("${thrift.port}")
//    private int port;
//    @Value("${thrift.minWorkerThreads}")
//    private int minThreads;
//    @Value("${thrift.maxWorkerThreads}")
//    private int maxThreads;

    /**
     * 监听的端口
    **/
    @Value("${server.thrift.port}")
    private Integer port;

    /**
     * 线程池最小线程数
     **/
    @Value("${server.thrift.min-thread-pool}")
    private Integer minThreads;

    /**
     * 线程池最大线程数
     **/
    @Value("${server.thrift.max-thread-pool}")
    private Integer maxThreads;

    private TBinaryProtocol.Factory protocolFactory;
    private TTransportFactory transportFactory;

    @Autowired
    private RPCDateServiceImpl rpcDateService;

    public void init() {
        protocolFactory = new TBinaryProtocol.Factory();
        transportFactory = new TTransportFactory();
    }

    public void start() {
        RPCDateService.Processor processor = new RPCDateService.Processor<RPCDateService.Iface>(rpcDateService);
        init();
        try {
            TServerTransport transport = new TServerSocket(port);
            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(transport);
            tArgs.processor(processor)
                    .protocolFactory(protocolFactory)
                    .transportFactory(transportFactory)
                    .minWorkerThreads(minThreads)
                    .maxWorkerThreads(maxThreads);
            TServer server = new TThreadPoolServer(tArgs);
            logger.info("thrift服务启动成功, 端口={}", port);
            server.serve();
        } catch (Exception e) {
            logger.error("thrift服务启动失败", e);
        }
    }
}

