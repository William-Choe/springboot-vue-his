package com.cui.springboot_his.Service;

import com.cui.springboot_his.Model.ResultMap;

public interface MedicalRecordService {
    ResultMap findPatientNotDiagnose(Integer doctor_id);

    ResultMap findPatientHaveDiagnose(Integer doctor_id);

    ResultMap findPatientWithMedicalRecord(Integer patient_id);

    ResultMap diagnose(Integer registration_id, String zs, String xbs, String xbzlqk, String jws, String gms, String tgjc, String cbzd);
}