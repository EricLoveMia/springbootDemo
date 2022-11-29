package com.loveprogrammer.springboot.websocket.tomcat.message;

/**
 *
 * @version 1.0.0
 * @description: 发送消息给一个用户
 * @author: eric
 * @date: 2022-11-29 14:10
 **/
public class SendToUserRequest implements Message{

    public static final String TYPE = "SEND_TO_USER_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;

    public String getMsgId() {
        return msgId;
    }

    public SendToUserRequest setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SendToUserRequest setContent(String content) {
        this.content = content;
        return this;
    }
}
