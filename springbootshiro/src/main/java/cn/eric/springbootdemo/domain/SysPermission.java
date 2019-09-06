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
 * @ClassName: SysPermission
 * @Description: TODO
 * @company lsj
 * @date 2019/5/14 17:29
 **/
@Entity
@Getter
@Setter
@ToString
public class SysPermission implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    /** 资源类型：一般分 菜单和按钮 */
    @Column(columnDefinition="enum('menu','button')")
    private String resourceType;
    private String url;

    /** 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view */
    private String permission;
    private Long parentId;
    private String parentIds;
    private Boolean available = Boolean.FALSE;

    @ManyToMany
    @JoinTable(name="SysRolePermission",
            joinColumns={@JoinColumn(name="permissionId")},
            inverseJoinColumns={@JoinColumn(name="roleId")})
    private List<SysRole> roles;

    // Getter & Setter
}