package com.loveprogrammer.springboot.thrift.client.service;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @ClassName RPCThriftClient
 * @Description: RPCThriftClient
 * @Author YCKJ2725
 * @Date 2021/8/2
 * @Version V1.0
 **/
public class RPCThriftClient {
    private RPCDateService.Client client;
    private TBinaryProtocol protocol;
    private TSocket transport;
    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * 初始化客户端
     */
    public void init() throws TTransportException {
        transport = new TSocket(host, port);
        //使用二进制协议
        protocol = new TBinaryProtocol(transport);
        client = new RPCDateService.Client(protocol);
    }

    public RPCDateService.Client getRPCThriftService() {
        return client;
    }

    public void open() throws TTransportException {
        transport.open();
    }

    public void close() {
        transport.close();
    }
}
