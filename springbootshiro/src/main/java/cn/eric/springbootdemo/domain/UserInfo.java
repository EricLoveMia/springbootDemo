package cn.eric.springbootdemo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: UserInfo
 * @Description: TODO
 * @company lsj
 * @date 2019/5/14 17:23
 **/
@Entity
@Getter
@Setter
@ToString
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue
    private Integer uid;

    @Column(unique = true)
    private String username;
    private String name;
    private String password;
    private String salt;
    //用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
    private byte state;

    @ManyToMany(fetch = FetchType.EAGER) // 立即从数据库中进行加载数据
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "uid")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roleList;

    public String getCredentialsSalt() {
        return this.username + this.salt;
    }
}
