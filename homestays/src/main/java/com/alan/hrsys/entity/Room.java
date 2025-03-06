package com.alan.hrsys.entity;


import jakarta.persistence.*;

@Entity
@Table(name="rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略为自增
    private Integer room_id;

    @Column
    private String room_num;

    @Column
    private String room_type;

    @Column
    private String room_price;

    @Column
    private String room_image_url;

    @Column
    private String is_occupied;

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
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

    public String getRoom_price() {
        return room_price;
    }

    public void setRoom_price(String room_price) {
        this.room_price = room_price;
    }

    public String getRoom_image_url() {
        return room_image_url;
    }

    public void setRoom_image_url(String room_image_url) {
        this.room_image_url = room_image_url;
    }

    public String getIs_occupied() {
        return is_occupied;
    }

    public void setIs_occupied(String is_occupied) {
        this.is_occupied = is_occupied;
    }
}
