package com.cui.springboot_his.Dao;

import com.cui.springboot_his.Entity.RegisteredLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredLevelDao extends JpaRepository<RegisteredLevel, Integer> {

}
