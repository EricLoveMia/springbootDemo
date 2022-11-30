package com.loveprogrammer.springboot.thrift.annotation.server;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftStruct;

/**
 * @ClassName User
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/8/12
 * @Version V1.0
 **/
@ThriftStruct
public final class User {

    private String name;

    private String email;

    @ThriftField(1)
    public String getName() {
        return name;
    }

    @ThriftField
    public void setName(String name) {
        this.name = name;
    }

    @ThriftField(2)
    public String getEmail() {
        return email;
    }

    @ThriftField
    public void setEmail(String email) {
        this.email = email;
    }
}
