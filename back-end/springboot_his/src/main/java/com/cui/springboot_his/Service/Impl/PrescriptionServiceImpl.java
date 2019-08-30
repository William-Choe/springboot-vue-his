package com.cui.springboot_his.Service.Impl;

import com.cui.springboot_his.Dao.DrugDao;
import com.cui.springboot_his.Dao.TreatmentPlanDao;
import com.cui.springboot_his.Entity.Drug;
import com.cui.springboot_his.Model.ResultMap;
import com.cui.springboot_his.Service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    private final DrugDao drugDao;
    private final TreatmentPlanDao treatmentPlanDao;

    @Autowired
    public PrescriptionServiceImpl(DrugDao drugDao, TreatmentPlanDao treatmentPlanDao) {
        this.drugDao = drugDao;
        this.treatmentPlanDao = treatmentPlanDao;
    }

    @Override
    public ResultMap getMedicineInfo() {
        List<Drug> list = drugDao.findAll();
        List<Map> mapList = new LinkedList<>();
        for (Drug drug : list) {
            Map<String, Object> drugMap = new HashMap<>();
            drugMap.put("drug_id", drug.getId());
            drugMap.put("name", drug.getName());
            drugMap.put("unit", drug.getUnit());
            drugMap.put("price", drug.getPrice());
            mapList.add(drugMap);
        }
        return new ResultMap().success().message("药品信息").data(mapList);
    }

    @Override
    public ResultMap prescribe(Integer medicalRecord_id, String prescribeName, String drug_str) {
        treatmentPlanDao.prescribe(medicalRecord_id, prescribeName, drug_str);
        return new ResultMap().success().message("添加处方成功！");
    }
}
