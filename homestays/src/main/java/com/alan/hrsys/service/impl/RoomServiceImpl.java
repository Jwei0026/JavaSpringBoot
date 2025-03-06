package com.alan.hrsys.service.impl;

import com.alan.hrsys.dao.RoomDao;
import com.alan.hrsys.entity.Room;
import com.alan.hrsys.service.RoomService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomDao rooDao;

    @Override
    public List<Room> search(Room condition) {
        List<Room> list =null;
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (condition.getRoom_id() != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("room_id").as(Integer.class), condition.getRoom_id());
                    predicates.add(predicate);
                }
                if (!StringUtils.isEmpty(condition.getRoom_num())) {
                    Predicate predicate = criteriaBuilder.like(root.get("room_num").as(String.class), "%" + condition.getRoom_num() + "%");
                    predicates.add(predicate);
                }
                if (!StringUtils.isEmpty(condition.getRoom_type())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("room_type").as(String.class), condition.getRoom_type());
                    predicates.add(predicate);
                }
                if (condition.getRoom_price() != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("room_price").as(Integer.class), condition.getRoom_price());
                    predicates.add(predicate);
                }
                if (condition.getIs_occupied() != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("is_occupied").as(Integer.class), condition.getIs_occupied());
                    predicates.add(predicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        };
        list = rooDao.findAll(specification);
        return list;
    }

    @Override
    public Room seachById(Integer id) {
        Room roo = rooDao.findById(id).get();
        return roo;
    }

    @Override
    public boolean add(Room dep) {
        Room newRoo =rooDao.save(dep);
        return newRoo !=null;
    }

    @Override
    public boolean update(Room dep) {
        Room newRoo = rooDao.save(dep);
        return newRoo !=null;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            rooDao.deleteById(id);
        }catch (Exception e){
        e.printStackTrace();
        return false;
        }
        return true;
    }
}
