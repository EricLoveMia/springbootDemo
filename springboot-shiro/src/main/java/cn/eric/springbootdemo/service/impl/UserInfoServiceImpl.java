package cn.eric.springbootdemo.service.impl;

import cn.eric.springbootdemo.dao.UserInfoDao;
import cn.eric.springbootdemo.domain.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: UserInfoServiceImpl
 * @Description: TODO
 * @company lsj
 * @date 2019/5/15 11:39
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {

        return userInfoDao.findByUsername(username);
    }
}
