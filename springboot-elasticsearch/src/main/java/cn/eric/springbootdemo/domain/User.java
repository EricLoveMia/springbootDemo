package cn.eric.springbootdemo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: User
 * @Description: TODO
 * @company lsj
 * @date 2019/4/23 14:37
 **/
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Integer age;

    @Column(name = "create_time")
    private Date createTime;

    public User() {

    }

    public User(String name, Integer age, Date createTime) {
        this.name = name;
        this.age = age;
        this.createTime = createTime;
    }
}
