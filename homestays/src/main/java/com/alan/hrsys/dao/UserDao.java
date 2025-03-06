package com.alan.hrsys.dao;

import com.alan.hrsys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    // 如果需要自定义查询或更新操作，可以在这里添加
    // 例如，如果需要根据用户身份更新用户名称，可以添加如下方法：

    @Modifying
    @Query("update User u set u.user_name = :name where u.identity = :identity")
    int updateNameByIdentity(String name, String identity);
}