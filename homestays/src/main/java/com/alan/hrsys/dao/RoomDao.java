package com.alan.hrsys.dao;

import com.alan.hrsys.entity.Room;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RoomDao extends JpaRepository<Room, Integer>, JpaSpecificationExecutor {
    List<Room> findAll(Specification specification);
}
