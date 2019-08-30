package com.cui.springboot_his.Controller;

import com.cui.springboot_his.Model.ResultMap;
import com.cui.springboot_his.Service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrescriptionController {
    private PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    /**
     * 获取药品信息
     * @return 药品信息
     */
    @GetMapping("/prescription/get-medicine-info")
    public ResultMap getMedicineInfo() {
        return prescriptionService.getMedicineInfo();
    }

    /**
     * 开处方
     * @param medicalRecord_id 患者病历ID
     * @param prescribe_name 处方名称 xxxx-成药处方/xxxx-草药处方
     * @param drug_str 药品字符串（药品ID-数量,药品ID-数量）
     * @return 添加处方状态
     */
    @PostMapping("/prescription/prescribe")
    public ResultMap prescribe(@RequestParam("medicalRecord_id") String medicalRecord_id, @RequestParam("prescribe_name") String prescribe_name,
                               @RequestParam("drug_str") String drug_str) {
        return prescriptionService.prescribe(Integer.parseInt(medicalRecord_id), prescribe_name, drug_str);
    }
}
