package com.cui.springboot_his.Service.Impl;

import com.cui.springboot_his.Dao.*;
import com.cui.springboot_his.Entity.Schedule;
import com.cui.springboot_his.Model.ResultMap;
import com.cui.springboot_his.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final MedicalRecordDao medicalRecordDao;
    private final RegisteredLevelDao registeredLevelDao;
    private final DepartmentDao departmentDao;
    private final ScheduleDao scheduleDao;
    private final RegistrationFormDao registrationFormDao;

    @Autowired
    public RegistrationServiceImpl(MedicalRecordDao medicalRecordDao, RegisteredLevelDao registeredLevelDao,
                                   DepartmentDao departmentDao, ScheduleDao scheduleDao, RegistrationFormDao registrationFormDao) {
        this.medicalRecordDao = medicalRecordDao;
        this.registeredLevelDao = registeredLevelDao;
        this.departmentDao = departmentDao;
        this.scheduleDao = scheduleDao;
        this.registrationFormDao = registrationFormDao;
    }

    @Override
    public ResultMap findAvailableMedicalRecordId() {
        return new ResultMap().success().message("可用病历号").data(String.valueOf(Collections.max(medicalRecordDao.findId()) + 1));
    }

    @Override
    public ResultMap getRegisteredLevel() {
        return new ResultMap().success().message("挂号级别").data(registeredLevelDao.findAll());
    }

    @Override
    public ResultMap getDepartment() {
        return new ResultMap().success().message("科室").data(departmentDao.findAll());
    }

    @Override
    public ResultMap findScheduleDoctor(Integer departmentId, Integer registeredLevelId) {
        List<Schedule> list = scheduleDao.findByDepartmentAndRegisteredLevel(departmentDao.findOne(departmentId), registeredLevelDao.findOne(registeredLevelId));
        List<Map> mapList = new LinkedList<>();
        for (Schedule schedule : list) {
            Map<String, Object> schedule_map = new HashMap<>();
            schedule_map.put("doctor", schedule.getDoctor().getName());
            schedule_map.put("doctor_id", schedule.getDoctor().getId());
            schedule_map.put("time", schedule.getTime());
            mapList.add(schedule_map);
        }
        if (list.size() == 0) {
            return new ResultMap().fail().message("无出诊信息");
        } else {
            return new ResultMap().success().message("可出诊医生").data(mapList);
        }
    }

    @Override
    public ResultMap registrate(String name, String sex, String age, Date birth, String occupation,
                                String idNumber, Integer department_id, Integer registeredLevel_id, Integer doctor_id, String phone) {
        registrationFormDao.registrate(name, sex, age, birth, occupation, idNumber, department_id, registeredLevel_id, doctor_id, phone);

        return new ResultMap().success().message("挂号成功！");
    }

    @Override
    public ResultMap getPatientInfo(Integer medicalRecord_id) {
        return new ResultMap().success().message("患者信息").data(medicalRecordDao.findOne(medicalRecord_id).getPatient());
    }

    @Override
    public ResultMap registrate_mrid(Integer medicalRecord_id, Integer department_id, Integer registeredLevel_id, Integer doctor_id) {
        registrationFormDao.registrate_mrid(medicalRecord_id, department_id, registeredLevel_id, doctor_id);

        return new ResultMap().success().message("挂号成功！");
    }
}
