package com.loveprogrammer.springboot.websocket.boot.message;

/**
 * @version 1.0.0
 * @description: 发送私聊消息
 * @author: eric
 * @date: 2022-11-29 14:05
 **/
public class SendToOneRequest implements Message{

    public static final String TYPE = "SEND_TO_ONE_REQUEST";

    /**
     * 发送给的用户
     */
    private String toUser;
    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;

    public static String getTYPE() {
        return TYPE;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
