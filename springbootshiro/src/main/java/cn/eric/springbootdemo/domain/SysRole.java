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
 * @ClassName: SysRole
 * @Description: TODO
 * @company lsj
 * @date 2019/5/14 17:27
 **/
@Entity
@Getter
@Setter
@ToString
public class SysRole implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String role;
    private String description;
    private Boolean available = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<SysPermission> permissions;

    // 如果需要的话，用于查询某个角色都分配给了哪些用户
    // 注意：当前的@JoinTable和UserInfo中的@JoinTable的name是一致的，但是joinColumns和inverseJoinColumns正好相反。
    @ManyToMany
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<UserInfo> userInfos;

}
