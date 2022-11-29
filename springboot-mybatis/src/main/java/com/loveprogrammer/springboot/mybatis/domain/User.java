package com.loveprogrammer.springboot.mybatis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


/**
 * @author Eric
 */
@Getter
@Setter
@ToString
public class User implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String name;

    private Boolean locked;

    public User(Long id, String username, String password, String name, Boolean locked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.locked = locked;
    }

    public User() {
    }

}