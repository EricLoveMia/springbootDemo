package cn.eric.springbootdemo.service;

import cn.eric.springbootdemo.domain.Order;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: OrderService
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 17:35
 **/
public interface OrderService {

    List<Order> getOrders();

    Page<Order> getOrdersByPage(Integer pageNo, Integer pageSize);
}

