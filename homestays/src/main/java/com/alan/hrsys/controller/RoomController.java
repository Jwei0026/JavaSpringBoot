package com.alan.hrsys.controller;

import com.alan.hrsys.entity.Room;
import com.alan.hrsys.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roo")
public class RoomController {
    @Autowired
    RoomService rooService;

    @GetMapping
    public List<Room> seach(Room condition){
        List<Room> list = rooService.search(condition);
        System.out.println(list);
        return list;
    }
    @GetMapping("{id}")
    public Room seacah(@PathVariable Integer id){
        Room roo =rooService.seachById(id);
        return roo;
    }

    @PutMapping
    public boolean add(@RequestBody Room roo){
        boolean flag = rooService.add(roo);
        return flag;

    }

    @PostMapping
    public boolean update(@RequestBody Room roo){
        boolean flag = rooService.update(roo);
        return flag;
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Integer id){
        boolean flag = rooService.delete(id);
        return flag;
    }
}
