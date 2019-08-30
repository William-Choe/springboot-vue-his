package com.cui.springboot_his.Controller;

import com.cui.springboot_his.Model.ResultMap;
import com.cui.springboot_his.Service.DispenseDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DispenseDrugController {
    private DispenseDrugService dispenseDrugService;

    @Autowired
    public DispenseDrugController(DispenseDrugService dispenseDrugService) {
        this.dispenseDrugService = dispenseDrugService;
    }

    /**
     * 获取应发药品信息
     * @param medicalRecord_id 病历ID
     * @param date 日期 mm-dd-yy
     * @return 返回此病历单应发药品列表
     */
    @PostMapping("/dispense-drug/get-medicine")
    public ResultMap findSchedule(@RequestParam("medicalRecord_id") String medicalRecord_id,
                                  @RequestParam("date") String date) {
        return dispenseDrugService.getDrug(Integer.parseInt(medicalRecord_id), date);
    }

    /**
     * 发药
     * @param medicalRecord_id 病历ID
     * @return 返回成功信息
     */
    @PostMapping("/dispense-drug")
    public ResultMap dispenseDrug(@RequestParam("medicalRecord_id") String medicalRecord_id) {
        return dispenseDrugService.dispenseDrug(Integer.parseInt(medicalRecord_id));
    }
}
