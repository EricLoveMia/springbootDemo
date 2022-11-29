package cn.eric.thrift.client;

import cn.eric.thrift.server.HelloService;
import cn.eric.thrift.server.User;
import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.swift.service.ThriftClientManager;

import java.net.InetSocketAddress;

/**
 * @author liyebing created on 16/12/17.
 * @version $Id$
 */
public class ClientMain {

    public static void main(String[] args) throws Exception {
        ThriftClientManager clientManager = new ThriftClientManager();
        FramedClientConnector connector = new FramedClientConnector(new InetSocketAddress("localhost", 8899));

        User user = new User();
        user.setName("liyebing");
        user.setEmail("test@163.com");

        HelloService helloService = clientManager.createClient(connector, HelloService.class).get();
        String hi = helloService.sayHello(user);
        System.out.println(hi);
    }

}
