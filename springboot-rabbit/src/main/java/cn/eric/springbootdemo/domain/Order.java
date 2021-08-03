package cn.eric.springbootdemo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: Order
 * @Description: TODO
 * @company lsj
 * @date 2019/7/16 17:12
 **/
@Getter
@Setter
@ToString
public class Order {

    private Integer id;
    private String userName;
    private String productName;
    private Integer status;
}
