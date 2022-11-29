package com.loveprogrammer.springboot.sign.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: AccessToken
 * @Description: TODO
 * @company lsj
 * @date 2019/5/17 13:34
 **/
@Data
@AllArgsConstructor
public class AccessToken {

    private String token;
    /**
     * 失效时间
     */
    private Date expireTime;
}
