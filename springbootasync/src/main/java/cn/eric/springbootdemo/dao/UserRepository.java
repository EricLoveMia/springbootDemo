package cn.eric.springbootdemo.dao;

import cn.eric.springbootdemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: UserRepository
 * @Description: TODO
 * @company lsj
 * @date 2019/5/10 15:29
 **/
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * from tbl_user where name = :userName", nativeQuery = true)
    User findUser2(@Param("userName") String userName);

    @Modifying
    @Transactional
    @Query(value = "update tbl_user set age = :age", nativeQuery = true)
    int updateAge(@Param("age") Integer age);
}
