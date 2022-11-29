package com.loveprogrammer.springboot.mybatis.controller;

import com.loveprogrammer.springboot.mybatis.domain.Order;
import com.loveprogrammer.springboot.mybatis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: OrderController
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 17:34
 **/
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }
}