package com.cui.springboot_his.Controller;

import com.cui.springboot_his.Model.ResultMap;
import com.cui.springboot_his.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
public class RegistrationController {
    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * 获取可用病历号
     * @return 返回病历号
     */
    @GetMapping("/registrate/find-medical-record-id")
    public ResultMap findMedicalRecordId() {
        return registrationService.findAvailableMedicalRecordId();
    }

    /**
     * 获取挂号级别
     * @return 返回挂号级别
     */
    @GetMapping("/registrate/get-registered-level")
    public ResultMap getRegisteredLevel() {
        return registrationService.getRegisteredLevel();
    }

    /**
     * 获取科室
     * @return 返回科室信息
     */
    @GetMapping("/registrate/get-department")
    public ResultMap getDepartment() {
        return registrationService.getDepartment();
    }

    /**
     * 查找排班医生
     * @param department_id 科室ID
     * @param registeredLevel_id 挂号级别ID
     * @return 返回符合信息排班医生
     */
    @PostMapping("/registrate/find-schedule-doctor")
    public ResultMap findSchedule(@RequestParam("department_id") String department_id,
                                  @RequestParam("registeredLevel_id") String registeredLevel_id) {
        return registrationService.findScheduleDoctor(Integer.parseInt(department_id), Integer.parseInt(registeredLevel_id));
    }

    /**
     * 获取患者信息
     * @param medicalRecord_id 病历ID
     * @return 返回患者病历信息
     */
    @PostMapping("/registrate/get-patient-info")
    public ResultMap getPatientInfo(@RequestParam("medicalRecord_id") String medicalRecord_id) {
        return registrationService.getPatientInfo(Integer.parseInt(medicalRecord_id));
    }

    /**
     * 挂号
     * @return 返回挂号状态
     */
    @PostMapping("/registrate")
    public ResultMap registrate(@RequestParam("name") String name, @RequestParam("sex") String sex, @RequestParam("age") String age,
                                @RequestParam("birth") String birth, @RequestParam("occupation") String occupation, @RequestParam("idNumber") String idNumber,
                                @RequestParam("department_id") String department_id, @RequestParam("registeredLevel_id") String registeredLevel_id,
                                @RequestParam("doctor_id") String doctor_id, @RequestParam("phone") String phone) {
        return registrationService.registrate(name, sex, age, Date.valueOf(birth), occupation, idNumber, Integer.parseInt(department_id),
                Integer.parseInt(registeredLevel_id), Integer.parseInt(doctor_id), phone);
    }

    /**
     * 挂号（已有病历号）
     * @param medicalRecord_id 病历ID
     * @param department_id 科室ID
     * @param registeredLevel_id 挂号级别ID
     * @param doctor_id 门诊医生ID
     * @return 返回挂号状态
     */
    @PostMapping("/registrate-by-medical-record-id")
    public ResultMap registrate_mrid(@RequestParam("medicalRecord_id") String medicalRecord_id, @RequestParam("department_id") String department_id,
                                                   @RequestParam("registeredLevel_id") String registeredLevel_id, @RequestParam("doctor_id") String doctor_id) {
        return registrationService.registrate_mrid(Integer.parseInt(medicalRecord_id), Integer.parseInt(department_id),
                Integer.parseInt(registeredLevel_id), Integer.parseInt(doctor_id));
    }
}
