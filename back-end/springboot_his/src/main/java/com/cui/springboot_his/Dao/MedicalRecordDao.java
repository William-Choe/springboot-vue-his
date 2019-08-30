package com.cui.springboot_his.Dao;

import com.cui.springboot_his.Entity.MedicalRecord;
import com.cui.springboot_his.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicalRecordDao extends JpaRepository<MedicalRecord, Integer> {

    @Procedure(name = "diagnose")
    void diagnose(@Param("n_registrationForm_id") Integer registration_id, @Param("n_zs") String zs,
                  @Param("n_xbs") String xbs, @Param("n_xbzlqk") String xbzlqk, @Param("n_jws") String jws,
                  @Param("n_gms") String gms, @Param("n_tgjc") String tgjc, @Param("n_cbzd") String cbzd,
                  @Param("n_jcjy") String jcjy, @Param("n_zysx") String zysx);

    /*查询病历ID全部值*/
    @Query(value = "select id from MedicalRecord ")
    List<Integer> findId();

    MedicalRecord findByPatient(Patient patient);
}
