package com.alan.hrsys.service;

import com.alan.hrsys.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> search(Order condition);

    public Order searchById(Integer id);

    public boolean add(Order order);

    public boolean update(Order order);

    public boolean delete(Integer id);
}
