package cn.eric.springbootdemo.dao;

import cn.eric.springbootdemo.domain.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: UserInfoDao
 * @Description: TODO
 * @company lsj
 * @date 2019/5/15 11:35
 **/
public interface UserInfoDao extends CrudRepository<UserInfo, Long> {

    UserInfo findByUsername(String username);

}
