package com.alan.hrsys.service;

import com.alan.hrsys.entity.User;

import java.util.List;

public interface UserService {


    List<User> search(User condition);


   User getById(Integer user_id);


    boolean add(User user);


    boolean update(User user);


    boolean delete(Integer id);
}