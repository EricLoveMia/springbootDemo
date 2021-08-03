package cn.eric.springbootdemo.controller;

import cn.eric.springbootdemo.service.AsyncTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: HelloController
 * @Description: TODO
 * @company lsj
 * @date 2019/4/19 18:09
 **/
@RestController
@RequestMapping("/hello")
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AsyncTaskService asyncTaskService;

    @RequestMapping("/world")
    public String index() {
        for (int i = 0; i < 15000; i++) {
            logger.trace(i + "日志输出 trace");
            logger.debug(i + "日志输出 debug");
            logger.info(i + "日志输出 info");
            logger.warn(i + "日志输出 warn");
            logger.error(i + "日志输出 error");
        }

        return "Hello World";
    }

    @GetMapping("/async/noParams")
    public String noParamAsync() {
        asyncTaskService.executeAsyncTask1();
        return "success";
    }

    @GetMapping("/async/hasParams")
    public String hasParamsAsync(String name) {

        asyncTaskService.executeAsyncTask2(name);

        return "success";
    }

    @GetMapping("/async/hasParamsAndReturn")
    public String hasParamsAndReturnAsync(String name) {

        Future<String> stringFuture = asyncTaskService.executeAsyncTask3(name);
        String result = null;
        try {
            result = stringFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

}
