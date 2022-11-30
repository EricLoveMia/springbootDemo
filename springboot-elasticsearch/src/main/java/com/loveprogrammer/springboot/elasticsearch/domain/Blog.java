package com.loveprogrammer.springboot.elasticsearch.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: Blog
 * @Description: TODO
 * @company lsj
 * @date 2019/6/26 18:08
 **/
@Data
@Accessors(chain = true)
@Document(indexName = "blog", type = "java")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String title;

    //@Field(type = FieldType.Date, format = DateFormat.basic_date)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date time;
}