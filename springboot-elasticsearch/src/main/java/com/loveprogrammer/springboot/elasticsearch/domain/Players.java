package com.loveprogrammer.springboot.elasticsearch.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: Players
 * @Description: TODO
 * @company lsj
 * @date 2019/7/8 15:06
 **/
@Data
@Document(indexName = "players", type = "players")
public class Players implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;
    /**
     * 不做全文检索字段 年龄
     */
    @Field(index = false)
    private Integer age;
    /**
     * 不做全文检索字段 收入
     */
    @Field(index = false)
    private Integer salary;
    @Field(fielddata = true)
    private String team;
    @Field(fielddata = true)
    private String position;

    public Players() {
    }

    public Players(String name, Integer age, Integer salary, String team, String position) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.team = team;
        this.position = position;
    }
}
