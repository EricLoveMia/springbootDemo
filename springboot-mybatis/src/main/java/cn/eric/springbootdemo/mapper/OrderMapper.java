package cn.eric.springbootdemo.mapper;

import cn.eric.springbootdemo.domain.Order;

import java.util.List;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: OrderMapper
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 17:37
 **/
public interface OrderMapper {

    List<Order> getOrders();
}
