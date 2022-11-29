package com.loveprogrammer.springboot.sign.domain;

import lombok.Data;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: UserInfo
 * @Description: TODO
 * @company lsj
 * @date 2019/5/17 15:46
 **/
@Data
public class UserInfo {

    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;

    private AccessToken accessToken;

    public UserInfo(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }
}
