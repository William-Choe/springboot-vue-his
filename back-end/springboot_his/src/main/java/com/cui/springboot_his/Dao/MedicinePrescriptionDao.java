package com.cui.springboot_his.Dao;

import com.cui.springboot_his.Entity.MedicinePrescription;
import com.cui.springboot_his.Entity.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicinePrescriptionDao extends JpaRepository<MedicinePrescription, Integer> {
    List<MedicinePrescription> findByTreatmentPlan(TreatmentPlan treatmentPlan);
}
