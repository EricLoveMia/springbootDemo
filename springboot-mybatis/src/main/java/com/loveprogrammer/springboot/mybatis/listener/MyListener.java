package com.loveprogrammer.springboot.mybatis.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: MyListener
 * @Description: TODO
 * @company lsj
 * @date 2019/5/10 16:47
 **/
@WebListener("MyListener")
public class MyListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(MyListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("监听器启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("监听器销毁");
    }
}
