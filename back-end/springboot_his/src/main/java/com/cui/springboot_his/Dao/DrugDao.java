package com.cui.springboot_his.Dao;

import com.cui.springboot_his.Entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugDao extends JpaRepository<Drug, Integer> {

    @Procedure(name = "dispensedrug")
    void dispenseDrug(@Param("n_medicalRecord_id") Integer medicalRecord_id);
}
