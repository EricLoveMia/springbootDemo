package com.loveprogrammer.springboot.sign.domain;

import lombok.Data;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: TokenInfo
 * @Description: TODO
 * @company lsj
 * @date 2019/5/17 13:36
 **/
@Data
public class TokenInfo {
    /**
     * token类型 api:0  user:1
     */
    private Integer tokenType;

    private AppInfo appInfo;

    private UserInfo userInfo;

}
