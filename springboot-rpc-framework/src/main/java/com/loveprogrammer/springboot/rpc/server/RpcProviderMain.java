package com.loveprogrammer.springboot.rpc.server;

import com.loveprogrammer.springboot.rpc.inter.HelloService;

/**
 * 服务的启动
 *
 * @author liyebing created on 16/12/25.
 * @version $Id$
 */
public class RpcProviderMain {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        ProviderReflect.provider(service, 8083);
    }

}
