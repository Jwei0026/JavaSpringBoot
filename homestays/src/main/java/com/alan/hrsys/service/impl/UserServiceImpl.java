package com.alan.hrsys.service.impl;

import com.alan.hrsys.dao.UserDao; // 假设您有一个UserDao
import com.alan.hrsys.entity.User;
import com.alan.hrsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
     UserDao userDao; // 注入UserDao

    @Override
    public List<User> search(User condition) {
        List<User> list=null;
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (condition.getUser_id() != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("user_id").as(Integer.class), condition.getUser_id());
                    predicates.add(predicate);
                }

                if (!StringUtils.isEmpty(condition.getUser_name())) {
                    Predicate predicate = criteriaBuilder.like(root.get("user_name").as(String.class), "%" + condition.getUser_name() + "%");
                    predicates.add(predicate);
                }
                if (!StringUtils.isEmpty(condition.getIdentity())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("identity").as(String.class), condition.getIdentity());
                    predicates.add(predicate);
                }
                if (!StringUtils.isEmpty(condition.getContact_info())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("contact_info").as(String.class), condition.getContact_info());
                    predicates.add(predicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        };

         list = userDao.findAll(specification);
        return list;
    }
    @Override
    public User getById(Integer user_id) {
        return userDao.findById(user_id).orElse(null); // 使用orElse避免NoSuchElementException异常
    }

    @Override
    public boolean add(User user) {
        userDao.save(user); // 保存用户，无需检查返回值是否为null
        return true;
    }

    @Override
    public boolean update(User user) {
        userDao.save(user); // 更新用户，无需检查返回值是否为null
        return true;
    }

    @Override
    public boolean delete(Integer user_id) {
        try {
            userDao.deleteById(user_id); // 删除用户
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}