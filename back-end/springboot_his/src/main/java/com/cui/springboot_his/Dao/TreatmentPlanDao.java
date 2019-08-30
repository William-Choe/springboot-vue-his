package com.cui.springboot_his.Dao;

import com.cui.springboot_his.Entity.MedicalRecord;
import com.cui.springboot_his.Entity.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentPlanDao extends JpaRepository<TreatmentPlan, Integer> {
    @Procedure(name = "prescription")
    void prescribe(@Param("n_medicalRecord_id") Integer medicalRecord_id, @Param("n_prescribeName") String prescribe_name, @Param("drug_str") String drug_str);

    TreatmentPlan findByMedicalRecord(MedicalRecord medicalRecord);
}
