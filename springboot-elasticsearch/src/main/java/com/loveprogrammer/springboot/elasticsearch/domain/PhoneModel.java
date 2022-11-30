package com.loveprogrammer.springboot.elasticsearch.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: PhoneModel
 * @Description: TODO
 * @company lsj
 * com.fengwenyi.springbootelasticsearchexamplephone.model.PhoneModel
 * com.eric.springbootdemo.domain.PhoneModel
 * @date 2019/7/2 10:41
 **/
@Data
@Accessors(chain = true)
@Document(indexName = "springboot_elasticsearch_example_phone", type = "com.fengwenyi.springbootelasticsearchexamplephone.model.PhoneModel")
public class PhoneModel implements Serializable {
    private static final long serialVersionUID = -5087658155687251393L;

    /* ID */
    @Id
    private String id;

    /* 名称 */
    private String name;

    /* 颜色，用英文分号(;)分隔 */
    private String colors;

    /* 卖点，用英文分号(;)分隔 */
    private String sellingPoints;

    /* 价格 */
    private String price;

    /* 产量 */
    private Long yield;

    /* 销售量 */
    private Long sale;

    /* 上市时间 */
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date marketTime;

    /* 数据抓取时间 */
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}