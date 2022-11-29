package com.loveprogrammer.springboot.websocket.boot.message;

/**
 * @version 1.0.0
 * @description: 广播用户加入群聊
 * @author: eric
 * @date: 2022-11-29 14:01
 **/
public class UserJoinNoticeRequest implements Message{

    public static final String TYPE = "USER_JOIN_NOTICE_REQUEST";

    /**
     * 昵称
     */
    private String nickname;

    public static String getTYPE() {
        return TYPE;
    }

    public String getNickname() {
        return nickname;
    }

    public UserJoinNoticeRequest setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
}
