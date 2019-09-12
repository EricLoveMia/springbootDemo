package cn.eric.springbootsharding.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: Order
 * @Description: TODO
 * @company lsj
 * @date 2019/5/22 17:42
 **/
@Entity
@Table(name = "t_order")
@Data
public class Order {

    @Id
    private Long orderId;

    private Long userId;
}
