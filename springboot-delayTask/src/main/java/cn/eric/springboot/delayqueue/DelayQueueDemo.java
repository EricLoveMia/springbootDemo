package cn.eric.springboot.delayqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DelayQueueDemo
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/9/6
 * @Version V1.0
 **/
public class DelayQueueDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("00000001");
        list.add("00000002");
        list.add("00000003");
        list.add("00000004");
        list.add("00000005");

        DelayQueue<OrderDelay> queue = new DelayQueue<>();
        long start = System.currentTimeMillis();
        for (String s : list) {
            queue.put(new OrderDelay(s, TimeUnit.NANOSECONDS.convert(3, TimeUnit.SECONDS)));

            try {
                queue.take().print();
                System.out.println("After " + (System.currentTimeMillis() - start) + " MilliSeconds");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
