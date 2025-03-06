package com.alan.hrsys.entity;

import jakarta.persistence.*;

import java.util.Date;



@Entity
@Table(name = "users") // 指定表名为 "users"
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id; // 用户ID

    @Column(nullable = false, unique = true)
    private String user_name; // 用户名称

    @Column(nullable = false)
    private String identity; // 用户身份

    @Column(nullable = false)
    private String contact_info; // 用户联系方式

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }
}