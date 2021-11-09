package cn.eric.springboot.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName OrderDelay
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/9/6
 * @Version V1.0
 **/
public class OrderDelay implements Delayed {

    private String orderId;

    private long timeout;

    OrderDelay(String orderId, long timeout) {

        this.orderId = orderId;

        this.timeout = timeout + System.nanoTime();

    }

    /**
     * @MethodName: getDelay
     * @Description: 返回距离你自定义的超时时间还有多少
     * @Param: [unit]
     * @Return: long
     * @Author: YCKJ2725
     * @Date: 2021/9/6 19:23
     **/
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(timeout - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        if (other == this) {
            return 0;
        }
        OrderDelay t = (OrderDelay) other;
        long d = (getDelay(TimeUnit.NANOSECONDS) - t.getDelay(TimeUnit.NANOSECONDS));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }

    void print() {
        System.out.println(orderId + "编号的订单要删除啦。。。。");
    }
}
