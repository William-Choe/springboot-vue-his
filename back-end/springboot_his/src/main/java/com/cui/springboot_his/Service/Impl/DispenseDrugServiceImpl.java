package com.cui.springboot_his.Service.Impl;

import com.cui.springboot_his.Dao.DrugDao;
import com.cui.springboot_his.Dao.MedicalRecordDao;
import com.cui.springboot_his.Dao.MedicinePrescriptionDao;
import com.cui.springboot_his.Dao.TreatmentPlanDao;
import com.cui.springboot_his.Entity.MedicalRecord;
import com.cui.springboot_his.Entity.MedicinePrescription;
import com.cui.springboot_his.Entity.TreatmentPlan;
import com.cui.springboot_his.Model.ResultMap;
import com.cui.springboot_his.Service.DispenseDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class DispenseDrugServiceImpl implements DispenseDrugService {
    private final TreatmentPlanDao treatmentPlanDao;
    private final MedicinePrescriptionDao medicinePrescriptionDao;
    private final MedicalRecordDao medicalRecordDao;
    private final DrugDao drugDao;

    @Autowired
    public DispenseDrugServiceImpl(TreatmentPlanDao treatmentPlanDao, MedicinePrescriptionDao medicinePrescriptionDao,
                                   MedicalRecordDao medicalRecordDao, DrugDao drugDao) {
        this.treatmentPlanDao = treatmentPlanDao;
        this.medicinePrescriptionDao = medicinePrescriptionDao;
        this.medicalRecordDao = medicalRecordDao;
        this.drugDao = drugDao;
    }

    @Override
    public ResultMap getDrug(Integer medicalRecord_id, String date) {
        MedicalRecord medicalRecord = medicalRecordDao.findOne(medicalRecord_id);
        TreatmentPlan treatmentPlan = treatmentPlanDao.findByMedicalRecord(medicalRecord);
        List<MedicinePrescription> list = medicinePrescriptionDao.findByTreatmentPlan(treatmentPlan);
        List<Map> mapList = new LinkedList<>();
        for (MedicinePrescription medicinePrescription : list) {
            Map<String, Object> form_map = new HashMap<>();
            form_map.put("drug_name", medicinePrescription.getContent());
            form_map.put("number", medicinePrescription.getNumber());
            form_map.put("status", medicinePrescription.getState());
            form_map.put("prescription_name", treatmentPlan.getDetail());
            form_map.put("time", treatmentPlan.getTime());
            mapList.add(form_map);
        }
        return new ResultMap().success().message("获取处方药品信息").data(mapList);
    }

    @Override
    public ResultMap dispenseDrug(Integer medicalRecord_id) {
        drugDao.dispenseDrug(medicalRecord_id);
        return new ResultMap().success().message("发药成功!");
    }
}
