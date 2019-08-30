package com.cui.springboot_his.Dao;

import com.cui.springboot_his.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
