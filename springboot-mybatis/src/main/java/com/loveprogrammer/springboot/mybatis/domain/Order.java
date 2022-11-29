package com.loveprogrammer.springboot.mybatis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: Order
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 17:35
 **/
@Setter
@Getter
@ToString
public class Order {
    private Long id;
    private String orderNo;
    private Date createTime;

}
