package com.cui.springboot_his.Service.Impl;

import com.cui.springboot_his.Dao.*;
import com.cui.springboot_his.Entity.Invoice;
import com.cui.springboot_his.Entity.PaymentItem;
import com.cui.springboot_his.Model.ResultMap;
import com.cui.springboot_his.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final MedicalRecordDao medicalRecordDao;
    private final PaymentItemDao paymentItemDao;
    private final PatientDao patientDao;
    private final DrugDao drugDao;

    @Autowired
    public PaymentServiceImpl(MedicalRecordDao medicalRecordDao, PaymentItemDao paymentItemDao,
                              PatientDao patientDao, DrugDao drugDao) {
        this.medicalRecordDao = medicalRecordDao;
        this.paymentItemDao = paymentItemDao;
        this.patientDao = patientDao;
        this.drugDao = drugDao;
    }

    @Override
    public ResultMap findPatientInfoByMedicalRecordId(Integer medicalRecord_id) {
        return new ResultMap().success().message("患者信息").data(medicalRecordDao.findOne(medicalRecord_id).getPatient());
    }

    @Override
    public ResultMap getPaymentItem(Integer patient_id) {
        List<PaymentItem> list = paymentItemDao.findByStatusAndPatient("未缴费", patientDao.findOne(patient_id));
        List<Map> mapList = new LinkedList<>();
        for (PaymentItem paymentItem : list) {
            Map<String, Object> paymentItem_map = new HashMap<>();
            paymentItem_map.put("paymentItem_id", paymentItem.getId());
            paymentItem_map.put("name", paymentItem.getContent());
            paymentItem_map.put("price", drugDao.findOne(paymentItem.getItemId()).getPrice());
            paymentItem_map.put("total_price", paymentItem.getFee());
            paymentItem_map.put("number", (int) (paymentItem.getFee() / drugDao.findOne(paymentItem.getItemId()).getPrice()));
            paymentItem_map.put("status", paymentItem.getStatus());
            paymentItem_map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paymentItem.getTime()));
            mapList.add(paymentItem_map);
        }
        return new ResultMap().success().message("应缴费项目").data(mapList);
    }

    @Override
    public ResultMap pay(Integer paymentItem_id, Integer others_id) {
        paymentItemDao.pay(paymentItem_id, others_id);
        return new ResultMap().success().message("支付成功！");
    }
}
