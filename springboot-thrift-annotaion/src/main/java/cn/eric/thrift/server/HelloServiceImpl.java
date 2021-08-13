package cn.eric.thrift.server;

/**
 * @ClassName HelloServiceImpl
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/8/12
 * @Version V1.0
 **/
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(User user) {
        return "hello," + user.getName() + user.getEmail();
    }
}
