package com.loveprogrammer.springboot.elasticsearch.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: EsBlog
 * @Description: TODO
 * @company lsj
 * @date 2019/7/2 15:00
 **/
/*@Document注解里面的几个属性，类比mysql的话是这样：
	index –> DB
	type –> Table
	Document –> row
*/
@Data
@Document(indexName = "esblog", type = "esblog")
public class EsBlog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @Id注解加上后，在Elasticsearch里相应于该列就是主键了，在查询时就可以直接用主键查询 主键, 注意这个搜索是id类型是string，与我们常用的不同
     */
    @Id
    private String id;
    /** 不做全文检索字段 */
    /**
     * Blog 实体的 id，这儿增加了一个blog的id属性
     */
    @Field(index = false)
    private Long blogId;
    private String title;
    private String summary;
    private String content;
    private String tags;
    /**
     * 不做全文检索字段 评论数
     */
    @Field(index = false)
    private Long commentSize;

}