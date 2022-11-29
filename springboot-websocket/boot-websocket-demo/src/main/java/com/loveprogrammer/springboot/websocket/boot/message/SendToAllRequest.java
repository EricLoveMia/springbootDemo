package com.loveprogrammer.springboot.websocket.boot.message;

/**
 * @version 1.0.0
 * @description: 发送群聊消息
 * @author: eric
 * @date: 2022-11-29 14:06
 **/
public class SendToAllRequest implements Message {

    public static final String TYPE = "SEND_TO_ALL_REQUEST";

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
