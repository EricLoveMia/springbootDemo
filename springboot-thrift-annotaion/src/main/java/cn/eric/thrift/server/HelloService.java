package cn.eric.thrift.server;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;

/**
 * @ClassName HelloService
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/8/12
 * @Version V1.0
 **/
@ThriftService("HelloService")
public interface HelloService {

    @ThriftMethod
    String sayHello(@ThriftField(name = "user") User user);
}
