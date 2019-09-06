package cn.eric.springbootsharding.controller;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: OrderController
 * @Description: TODO
 * @company lsj
 * @date 2019/5/22 17:47
 **/
import cn.eric.springbootsharding.domain.Order;
import cn.eric.springbootsharding.mapper.OrderMapper;
import cn.eric.springbootsharding.repositorty.OrderRepository;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private KeyGenerator keyGenerator;

    @RequestMapping("/create")
    public Object add() {
//        for (int i = 0; i < 10; i++) {
//            Order order = new Order();
//            order.setUserId((long) i);
//            order.setOrderId((long) i);
//            orderRepository.save(order);
//        }
//        for (int i = 10; i < 20; i++) {
//            Order order = new Order();
//            order.setUserId((long) i + 1);
//            order.setOrderId((long) i);
//            orderRepository.save(order);
//        }

//        for (int i = 0; i < 30; i++) {
//            Order order = new Order();
//            order.setOrderId(keyGenerator.generateKey().longValue());
//            order.setUserId(keyGenerator.generateKey().longValue());
//            orderRepository.save(order);
//        }

        return "success";
    }

    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public Object insert() {
        for (int i = 40; i < 60; i++) {
            Order order = new Order();
            order.setUserId((long) i);
            order.setOrderId((long) i);
            orderMapper.insert(order);
        }
        for (int i = 40; i < 60; i++) {
            Order order = new Order();
            order.setUserId((long) i + 1);
            order.setOrderId((long) i);
            orderMapper.insert(order);
        }

        return "success";
    }

    @RequestMapping("queryById")
    public List<Order> queryById(String orderIds) {
        List<String> strings = Arrays.asList(orderIds.split(","));
        List<Long> orderIdList = strings.stream().map(item -> Long.parseLong(item)).collect(Collectors.toList());
        return orderMapper.queryById(orderIdList);
    }

    @RequestMapping("query")
    private Object queryAll() {

        return orderRepository.findAll();
    }
}
