package cn.eric.springbootdemo.future.discount;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: ExchangeDemo
 * @Description: TODO
 * @company lsj
 * @date 2019/5/29 16:11
 **/
//汇率的服务 定义也是1秒
public class ExchangeDemo {

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static double getRate(String source, String target) {
        delay();
        return 10;
    }

}