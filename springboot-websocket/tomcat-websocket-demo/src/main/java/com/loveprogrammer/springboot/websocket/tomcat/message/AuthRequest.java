package com.loveprogrammer.springboot.websocket.tomcat.message;

/**
 * @version 1.0.0
 * @description: 认证消息体
 * @author: eric
 * @date: 2022-11-29 13:58
 **/
public class AuthRequest implements Message{

    public static final String TYPE = "AUTH_REQUEST";

    /**
     * 认证 Token
     */
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public AuthRequest setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
}
