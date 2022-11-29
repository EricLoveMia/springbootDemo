package com.loveprogrammer.springboot.websocket.tomcat.handler;

import com.loveprogrammer.springboot.websocket.tomcat.message.Message;

import javax.websocket.Session;

/**
 *
 * @version 1.0.0
 * @description: 消息处理接口
 * @author: eric
 * @date: 2022-11-29 14:14
 **/
public interface MessageHandler<T extends Message> {

    /**
     * 执行处理消息
     *
     * @param session 会话
     * @param message 消息
     */
    void execute(Session session, T message);

    /**
     * @return 消息类型，即每个 Message 实现类上的 TYPE 静态字段
     */
    String getType();
}
