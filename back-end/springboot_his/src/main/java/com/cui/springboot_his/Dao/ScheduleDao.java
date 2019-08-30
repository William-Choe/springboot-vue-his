package com.cui.springboot_his.Dao;

import com.cui.springboot_his.Entity.Department;
import com.cui.springboot_his.Entity.RegisteredLevel;
import com.cui.springboot_his.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleDao extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByDepartmentAndRegisteredLevel(Department department, RegisteredLevel registeredLevel);
}
