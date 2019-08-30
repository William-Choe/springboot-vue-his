package com.cui.springboot_his.Controller;

import com.cui.springboot_his.Model.ResultMap;
import com.cui.springboot_his.Service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalRecordController {
    private MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }


    /**
     * 查找医生待诊患者
     * @param doctor_id 医生ID
     * @return 挂号单ID，病人ID，病人姓名
     */
    @PostMapping("/medical-record/find-patient-not-diagnose")
    public ResultMap findPatientNotDiagnose(@RequestParam("doctor_id") String doctor_id) {
        return medicalRecordService.findPatientNotDiagnose(Integer.parseInt(doctor_id));
    }

    /**
     * 查找医生已诊患者
     * @param doctor_id 医生ID
     * @return 挂号单ID，病人ID，病人姓名
     */
    @PostMapping("/medical-record/find-patient-diagnosed")
    public ResultMap findPatientDiagnosed(@RequestParam("doctor_id") String doctor_id) {
        return medicalRecordService.findPatientHaveDiagnose(Integer.parseInt(doctor_id));
    }

    /**
     * 查找病人的病历单
     * @param patient_id 病人ID
     * @return 返回病历单内容
     */
    @PostMapping("/medical-record/find-patient-record")
    public ResultMap findPatientMedicalRecord(@RequestParam("patient_id") String patient_id) {
        return medicalRecordService.findPatientWithMedicalRecord(Integer.parseInt(patient_id));
    }

    /**
     * 诊断
     * @param registration_id 挂号单ID
     * @param zs ...诊断的信息内容
     * @return 诊断状态
     */
    @PostMapping("/medical-record/diagnose")
    public ResultMap diagnose(@RequestParam("registrationForm_id") String registration_id, @RequestParam("zs") String zs,
                              @RequestParam("xbs") String xbs, @RequestParam("xbzlqk") String xbzlqk, @RequestParam("jws") String jws,
                              @RequestParam("gms") String gms, @RequestParam("tgjc") String tgjc, @RequestParam("cbzd") String cbzd) {
        return medicalRecordService.diagnose(Integer.parseInt(registration_id), zs, xbs, xbzlqk, jws, gms, tgjc, cbzd);
    }
}
