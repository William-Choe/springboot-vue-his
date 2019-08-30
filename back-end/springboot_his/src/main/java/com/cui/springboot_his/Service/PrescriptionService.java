package com.cui.springboot_his.Service;

import com.cui.springboot_his.Model.ResultMap;

public interface PrescriptionService {
    ResultMap getMedicineInfo();

    ResultMap prescribe(Integer medicalRecord_id, String prescribeName, String drug_str);
}
