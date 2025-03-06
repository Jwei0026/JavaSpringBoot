package com.alan.hrsys.controller;

import com.alan.hrsys.entity.User;
import com.alan.hrsys.service.UserService; // 假设您有一个UserService
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("users")

public class UserController {

    @Autowired
    private UserService userService; // 注入UserService

    @GetMapping
    public List<User> search(User condition) {
        List<User> list = userService.search(condition);
        System.out.println(list);
        return list;
    }

    @GetMapping("/{user_id}")
    public User getUserById(@PathVariable Integer user_id) {
        return userService.getById(user_id);
    }


    @PostMapping
    public boolean addUser(@RequestBody User user) {
        return userService.add(user);
    }

    @PutMapping
    public boolean updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/{user_id}")
    public boolean deleteUser(@PathVariable Integer user_id) {
        return userService.delete(user_id);
    }
}