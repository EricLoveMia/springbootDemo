package com.loveprogrammer.springboot.sign.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

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
public class User implements Serializable {

    private Long id;
    private String name;
    private Integer age;
}
