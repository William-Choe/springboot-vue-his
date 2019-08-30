package com.cui.springboot_his.Service;

import com.cui.springboot_his.Model.ResultMap;

public interface DispenseDrugService {
    ResultMap getDrug(Integer medicalRecord_id, String date);

    ResultMap dispenseDrug(Integer medicalRecord_id);
}
