package com.loveprogrammer.springboot.websocket.tomcat.message;

/**
 * @version 1.0.0
 * @description: 发送消息结果响应
 * @author: eric
 * @date: 2022-11-29 14:09
 **/
public class SendResponse implements Message{

    public static final String TYPE = "SEND_RESPONSE";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应提示
     */
    private String message;

    public String getMsgId() {
        return msgId;
    }

    public SendResponse setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public SendResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public SendResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
