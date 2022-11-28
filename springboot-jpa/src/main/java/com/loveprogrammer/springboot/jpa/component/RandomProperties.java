package com.loveprogrammer.springboot.jpa.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: RandomProperties
 * @Description: TODO
 * @company lsj
 * @date 2019/4/23 17:32
 **/
@Component
@ConfigurationProperties(prefix = "test")
@PropertySource("classpath:test.properties")
public class RandomProperties {

    private Integer a;
    private Integer b;
    private Integer c;
    private Long d;
    private String value;
    private String uuid;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Long getD() {
        return d;
    }

    public void setD(Long d) {
        this.d = d;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "RandomProperties{" + "a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", value='" + value + '\'' + ", uuid='" + uuid + '\'' + '}';
    }
}
