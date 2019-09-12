package cn.eric.springbootdemo.service.impl;

import cn.eric.springbootdemo.domain.Order;
import cn.eric.springbootdemo.mapper.OrderMapper;
import cn.eric.springbootdemo.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: OrderServiceImpl
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 17:36
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getOrders() {

        return orderMapper.getOrders();
    }

    @Override
    public Page<Order> getOrdersByPage(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return null;
    }


}
