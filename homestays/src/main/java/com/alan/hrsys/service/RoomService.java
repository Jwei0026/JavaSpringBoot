package com.alan.hrsys.service;

import com.alan.hrsys.entity.Room;

import java.util.List;

public interface RoomService {

    public List<Room> search(Room condition);

    public Room seachById(Integer id);

    public boolean add(Room dep);

    public boolean update(Room dep);

    public boolean delete(Integer id);


}
