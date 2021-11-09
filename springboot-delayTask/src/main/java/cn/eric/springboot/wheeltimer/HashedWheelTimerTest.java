package cn.eric.springboot.wheeltimer;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName HashedWheelTimerTest
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/9/6
 * @Version V1.0
 **/
public class HashedWheelTimerTest {

    static class MyTimerTask implements TimerTask {

        boolean flag;

        String name;

        public MyTimerTask(boolean flag, String name) {
            this.flag = flag;
            this.name = name;
        }

        @Override
        public void run(Timeout timeout) throws Exception {
            // TODO Auto-generated method stub
            System.out.println(name + "要去数据库删除订单了。。。。");
            this.flag = false;
        }

    }

    public static void main(String[] argv) {

        MyTimerTask timerTask = new MyTimerTask(true, "task1");
        Timer timer = new HashedWheelTimer();
        timer.newTimeout(timerTask, 5, TimeUnit.SECONDS);

        MyTimerTask timerTask2 = new MyTimerTask(true, "task2");
        Timer timer2 = new HashedWheelTimer();
        timer2.newTimeout(timerTask2, 10, TimeUnit.SECONDS);


        int i = 1;
        while (i < 100) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(i + "秒过去了");
            i++;
        }

    }
}
