package com.cui.springboot_his.Dao;

import com.cui.springboot_his.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao extends JpaRepository<Patient, Integer> {

}
