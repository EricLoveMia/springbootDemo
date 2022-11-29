package com.loveprogrammer.springboot.websocket.boot.message;

/**
 * @version 1.0.0
 * @description: 认证响应信息
 * @author: eric
 * @date: 2022-11-29 14:00
 **/
public class AuthResponse implements Message{

    public static final String TYPE = "AUTH_RESPONSE";

    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应提示
     */
    private String message;

    public Integer getCode() {
        return code;
    }

    public AuthResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AuthResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
