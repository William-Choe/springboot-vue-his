package com.cui.springboot_his.Service;

import com.cui.springboot_his.Model.ResultMap;

import java.sql.Date;

public interface RegistrationService {
    ResultMap findAvailableMedicalRecordId();

    ResultMap getRegisteredLevel();

    ResultMap getDepartment();

    ResultMap findScheduleDoctor(Integer departmentId, Integer registeredLevelId);

    ResultMap registrate(String name, String sex, String age, Date birth, String occupation,
                         String idNumber, Integer department_id, Integer registeredLevel_id, Integer doctor_id,
                         String phone);

    ResultMap getPatientInfo(Integer medicalRecord_id);

    ResultMap registrate_mrid(Integer medicalRecord_id, Integer department_id, Integer registeredLevel_id, Integer doctor_id);
}
