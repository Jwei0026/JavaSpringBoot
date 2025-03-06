package com.alan.hrsys.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "`Orders`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;

    @Column
    private String user_name;

    @Column
    private Date check_in_time;

    @Column
    private String contact_info;

    @Column
    private String room_num;

    @Column
    private String room_type;


    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Date getCheck_in_time() {
        return check_in_time;
    }

    public void setCheck_in_time(Date check_in_time) {
        this.check_in_time = check_in_time;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    public String getRoom_num() {
        return room_num;
    }

    public void setRoom_num(String room_num) {
        this.room_num = room_num;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }
}
