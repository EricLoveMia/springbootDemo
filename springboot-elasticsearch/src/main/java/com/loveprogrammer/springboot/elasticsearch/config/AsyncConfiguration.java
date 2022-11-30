package com.loveprogrammer.springboot.elasticsearch.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: AsyncConfiguration
 * @Description: TODO
 * @company lsj
 * @date 2019/5/29 14:51
 **/
@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {

    /**
     * 定义线程池
     *
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.initialize();

        return taskExecutor;
    }


    /**
     * 异步方法抛出异常时处理器
     * return null: 使用默认的处理器，打印异常信息和堆栈
     * 也可以自定义自己的异常处理器
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
//        return new MyAsyncUncaughtExceptionHandler();
    }


    /**
     * 自定义异常处理
     * 没有返回值的异步方法当抛异常时会走AsyncUncaughtExceptionHandler
     */
    class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            System.out.println("Exception message - " + throwable.getMessage());
            System.out.println("Method name - " + method.getName());
            for (Object param : objects) {
                System.out.println("Parameter value - " + param);
            }
        }
    }
}