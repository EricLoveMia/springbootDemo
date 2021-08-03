package cn.eric.springbootdemo.service.impl;

import cn.eric.springbootdemo.domain.UserInfo;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: UserInfoService
 * @Description: TODO
 * @company lsj
 * @date 2019/5/15 11:37
 **/
public interface UserInfoService {

    UserInfo findByUsername(String username);
}
