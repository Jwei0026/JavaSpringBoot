package com.alan.hrsys.service.impl;

import com.alan.hrsys.dao.OrderDao;
import com.alan.hrsys.entity.Order;
import com.alan.hrsys.service.OrderService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

//    @Override
//    public List<Order> search() {
//        List<Order> list = orderDao.findAll();
//        return list;
//    }
    @Override
    public List<Order> search(Order condition) {
        List<Order> list=null;

        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (condition.getOrder_id() != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("order_id").as(Integer.class), condition.getOrder_id());
                    predicates.add(predicate);
                }
                if (!StringUtils.isEmpty(condition.getUser_name())) {
                    Predicate predicate = criteriaBuilder.like(root.get("user_name").as(String.class),"%"+condition.getUser_name()+"%");
                    predicates.add(predicate);
                }
                if (!StringUtils.isEmpty(condition.getContact_info())) {
                    Predicate predicate = criteriaBuilder.like(root.get("contact_info").as(String.class),"%"+condition.getContact_info()+"%");
                    predicates.add(predicate);
                }
                if (!StringUtils.isEmpty(condition.getRoom_num())) {
                    Predicate predicate = criteriaBuilder.like(root.get("room_num").as(String.class),"%"+condition.getRoom_num()+"%");
                    predicates.add(predicate);
                }
                if (!StringUtils.isEmpty(condition.getRoom_type())) {
                    Predicate predicate = criteriaBuilder.like(root.get("room_type").as(String.class),"%"+condition.getRoom_type()+"%");
                    predicates.add(predicate);
                }

                if (!StringUtils.isEmpty(condition.getCheck_in_time())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("check_in_time").as(Date.class), condition.getCheck_in_time());
                    predicates.add(predicate);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        };
        list = orderDao.findAll(specification);
        return list;
    }



    @Override
    public Order searchById(Integer id) {
        Order order = orderDao.findById(id).get();
        return order;
    }

    @Override
    public boolean add(Order order) {
        Order neworder = orderDao.save(order);
        return neworder !=null ;
    }

    @Override
    public boolean update(Order order) {
        Order neworder = orderDao.save(order);
        return neworder != null;
    }

    @Override
    public boolean delete(Integer id) {
        try{
            orderDao.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
