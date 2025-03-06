package com.alan.hrsys.controller;

import com.alan.hrsys.entity.Order;
import com.alan.hrsys.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("order")
public class OrderControllers {
    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> search(Order condition) {
        System.out.println(condition);

        List<Order> list = orderService.search(condition);

        System.out.println(list);
        return list;
    }

//    @GetMapping
//    public List<Order> search(Order condition){
//        System.out.println("进入search（）");
//        List <Order> list = orderService.search(condition);
//        list.forEach(order -> System.out.println(order.getOrder_id() + ": " + order.getUser_name()));
//        System.out.println("search（）结束");
//        return list;
//    }

    @GetMapping("{id}")
    public Order search(@PathVariable Integer id) {
        Order  order = orderService.searchById(id);
        return order;
    }

    @PostMapping
    public boolean add(@RequestBody Order order) {
        boolean flag = orderService.add(order);
        return flag;

    }

    @PutMapping
    public boolean update(@RequestBody Order order) {
        boolean flag = orderService.update(order);
        return flag;
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Integer id) {
        boolean flag = orderService.delete(id);
        return flag;
    }
}
