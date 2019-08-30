package com.cui.springboot_his.Service.Impl;

import com.cui.springboot_his.Dao.PatientDao;
import com.cui.springboot_his.Dao.RegistrationFormDao;
import com.cui.springboot_his.Entity.Patient;
import com.cui.springboot_his.Entity.RegistrationForm;
import com.cui.springboot_his.Entity.Schedule;
import com.cui.springboot_his.Model.ResultMap;
import com.cui.springboot_his.Service.BackRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class BackRegistrationServiceImpl implements BackRegistrationService {
    private final RegistrationFormDao registrationFormDao;
    private final PatientDao patientDao;

    @Autowired
    public BackRegistrationServiceImpl(RegistrationFormDao registrationFormDao, PatientDao patientDao) {
        this.registrationFormDao = registrationFormDao;
        this.patientDao = patientDao;
    }

    @Override
    public ResultMap findRegistrationForm(Integer registrationForm_id) {
        RegistrationForm form = registrationFormDao.findOne(registrationForm_id);

        if (form == null) {
            return new ResultMap().fail().message("无此挂号单信息");
        } else {
            Map<String,Object> map = new HashMap<>();
            map.put("id", form.getId());
            map.put("time", new SimpleDateFormat("yyyy-MM-dd").format(form.getTime()));
            map.put("status", form.getStatus());
            map.put("department", form.getDepartment().getName());
            map.put("registeredLevel", form.getRegisteredLevel().getLevel());
            map.put("doctor", form.getDoctor().getName());
            map.put("patient_name", form.getPatient().getName());
            map.put("patient_idNumber", form.getPatient().getIdNumber());
            map.put("registrationForm_id", registrationForm_id);

            return new ResultMap().success().message("挂号单信息").data(map);
        }
    }

    @Override
    public ResultMap backRegistrate(Integer registrationForm_id) {
        registrationFormDao.back_registrate(registrationForm_id);
        return new ResultMap().success().message("退号成功");
    }
}
