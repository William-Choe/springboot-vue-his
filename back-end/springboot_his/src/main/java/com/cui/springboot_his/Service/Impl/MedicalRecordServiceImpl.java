package com.cui.springboot_his.Service.Impl;

import com.cui.springboot_his.Dao.DoctorDao;
import com.cui.springboot_his.Dao.MedicalRecordDao;
import com.cui.springboot_his.Dao.PatientDao;
import com.cui.springboot_his.Dao.RegistrationFormDao;
import com.cui.springboot_his.Entity.MedicalRecord;
import com.cui.springboot_his.Entity.Patient;
import com.cui.springboot_his.Entity.RegistrationForm;
import com.cui.springboot_his.Model.ResultMap;
import com.cui.springboot_his.Service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final RegistrationFormDao registrationFormDao;
    private final MedicalRecordDao medicalRecordDao;
    private final PatientDao patientDao;
    private final DoctorDao doctorDao;

    @Autowired
    public MedicalRecordServiceImpl(RegistrationFormDao registrationFormDao, MedicalRecordDao medicalRecordDao, PatientDao patientDao, DoctorDao doctorDao) {
        this.registrationFormDao = registrationFormDao;
        this.medicalRecordDao = medicalRecordDao;
        this.patientDao = patientDao;
        this.doctorDao = doctorDao;
    }

    @Override
    public ResultMap findPatientNotDiagnose(Integer doctor_id) {
        List<RegistrationForm> list = registrationFormDao.findByStatusAndDoctor("未就诊", doctorDao.findOne(doctor_id));
        List<Map> mapList = new LinkedList<>();
        for (RegistrationForm registrationForm : list) {
            Map<String, Object> form_map = new HashMap<>();
            form_map.put("form_id", registrationForm.getId());
            form_map.put("patient_id", registrationForm.getPatient().getId());
            form_map.put("name", registrationForm.getPatient().getName());
            mapList.add(form_map);
        }
        return new ResultMap().success().message("未就诊患者").data(mapList);
    }

    @Override
    public ResultMap findPatientHaveDiagnose(Integer doctor_id) {
        List<RegistrationForm> list = registrationFormDao.findByStatusAndDoctor("已就诊", doctorDao.findOne(doctor_id));
        List<Map> mapList = new LinkedList<>();
        for (RegistrationForm registrationForm : list) {
            Map<String, Object> form_map = new HashMap<>();
            form_map.put("form_id", registrationForm.getId());
            form_map.put("patient_id", registrationForm.getPatient().getId());
            form_map.put("name", registrationForm.getPatient().getName());
            mapList.add(form_map);
        }
        return new ResultMap().success().message("已就诊患者").data(mapList);
    }

    @Override
    public ResultMap findPatientWithMedicalRecord(Integer patient_id) {
        Patient patient = patientDao.findOne(patient_id);
        MedicalRecord medicalRecord = medicalRecordDao.findByPatient(patient);
        Map<String,Object> map = new HashMap<>();
        map.put("medicalRecord_id", medicalRecord.getId());
        map.put("patient_id", patient.getId());
        map.put("name", patient.getName());
        map.put("sex", patient.getSex());
        map.put("age", patient.getAge());
        map.put("zs", medicalRecord.getZs());
        map.put("xbs", medicalRecord.getXbs());
        map.put("xbzlqk", medicalRecord.getXbzlqk());
        map.put("jws", medicalRecord.getJws());
        map.put("gms", medicalRecord.getGms());
        map.put("tgjc", medicalRecord.getTgjc());
        map.put("cbzd", medicalRecord.getCbzd());

        return new ResultMap().success().message("患者信息").data(map);
    }

    @Override
    public ResultMap diagnose(Integer registration_id, String zs, String xbs, String xbzlqk, String jws, String gms, String tgjc, String cbzd) {
        medicalRecordDao.diagnose(registration_id, zs, xbs, xbzlqk, jws, gms, tgjc, cbzd, "无", "无");
        return new ResultMap().success().message("添加诊断成功！");
    }
}
