package com.cui.springboot_his.Service;

import com.cui.springboot_his.Model.ResultMap;

public interface PaymentService {
    ResultMap findPatientInfoByMedicalRecordId(Integer medicalRecord_id);

    ResultMap getPaymentItem(Integer patient_id);

    ResultMap pay(Integer patient_id, Integer others_id);
}
