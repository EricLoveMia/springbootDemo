package com.loveprogrammer.springboot.mybatis.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: MyHttpSessionListener
 * @Description: TODO
 * @company lsj
 * @date 2019/5/10 16:49
 **/
public class MyHttpSessionListener implements HttpSessionListener {

    private static Logger logger = LoggerFactory.getLogger(MyHttpSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.debug("session创建");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.debug("session销毁");
    }
}
