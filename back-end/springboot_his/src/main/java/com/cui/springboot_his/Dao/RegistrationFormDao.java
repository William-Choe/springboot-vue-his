package com.cui.springboot_his.Dao;

import com.cui.springboot_his.Entity.Doctor;
import com.cui.springboot_his.Entity.RegistrationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RegistrationFormDao extends JpaRepository<RegistrationForm, Integer> {
    @Procedure(name = "registration")
    void registrate(@Param("n_name") String name, @Param("n_sex") String sex, @Param("n_age") String age,
                    @Param("n_birth") Date date, @Param("n_occupation") String occupation, @Param("n_idNumber") String idNumber,
                    @Param("n_department_id") Integer department_id, @Param("n_registeredLevel_id") Integer registeredLevel_id,
                    @Param("n_doctor_id") Integer doctor_id, @Param("n_phone") String phone);

    @Procedure(name = "registration_mrid")
    void registrate_mrid(@Param("n_medicalRecord_id") Integer medicalRecord_id, @Param("n_department_id") Integer department_id,
                                       @Param("n_registeredLevel_id") Integer registeredLevel_id, @Param("n_doctor_id") Integer doctor_id);

    @Procedure(name = "back_registration")
    void back_registrate(@Param("n_registrationForm_id") Integer registrationForm_id);

    List<RegistrationForm> findByStatusAndDoctor(String status, Doctor doctor);
}
