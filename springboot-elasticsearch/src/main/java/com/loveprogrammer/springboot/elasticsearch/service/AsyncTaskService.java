package com.loveprogrammer.springboot.elasticsearch.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: AsyncTaskService
 * @Description: TODO
 * @company lsj
 * @date 2019/5/29 14:52
 **/
@Service
public class AsyncTaskService {

    /**
     * 无参无返回值
     */
    @Async
    public void executeAsyncTask1() {
        for (int i = 0; i < 5; i++) {
            System.out.println("executeAsyncTask1 = " + Thread.currentThread().getName() + "\t i=" + i);
            try {
                Thread.sleep(100);
                // int a = 1/0;
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * 有参无返回值
     *
     * @param name
     */
    @Async
    public void executeAsyncTask2(String name) {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " =\t" + Thread.currentThread().getName() + "\t i=" + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * 有返回值有参数
     * 这种情况下当方法抛异常可以在方法内部处理掉异常或者在调用future.get时处理异常
     *
     * @param value
     * @return
     */
    @Async
    public Future<String> executeAsyncTask3(String value) {
        Future<String> future;
        try {
            Thread.sleep(100);
            System.out.println(value + " =\t" + Thread.currentThread().getName());
            future = new AsyncResult<>(value.toUpperCase());
        } catch (InterruptedException e) {
            future = new AsyncResult<String>("error");
        }
        return future;
    }
}
