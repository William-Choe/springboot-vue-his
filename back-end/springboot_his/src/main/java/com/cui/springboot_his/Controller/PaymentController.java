package com.cui.springboot_his.Controller;

import com.cui.springboot_his.Model.ResultMap;
import com.cui.springboot_his.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * 获取患者信息
     * @param medicalRecord_id 病历单号
     * @return 患者信息
     */
    @PostMapping("/payment/find-patient-info")
    public ResultMap findPatientInfo(@RequestParam("medicalRecord_id") String medicalRecord_id) {
        return paymentService.findPatientInfoByMedicalRecordId(Integer.parseInt(medicalRecord_id));
    }

    /**
     * 获取该患者应缴费项目列表
     * @param patient_id 患者ID
     * @return 应缴费项目列表
     */
    @PostMapping("/payment/get-payment-item")
    public ResultMap getPaymentItem(@RequestParam("patient_id") String patient_id) {
        return paymentService.getPaymentItem(Integer.parseInt(patient_id));
    }

    /**
     * 支付应缴费项目
     * @param paymentItem_id 患者ID
     * @param others_id 操作员ID
     * @return 支付状态
     */
    @PostMapping("/payment/pay")
    public ResultMap pay(@RequestParam("paymentItem_id") String paymentItem_id, @RequestParam("others_id") String others_id) {
        return paymentService.pay(Integer.parseInt(paymentItem_id), Integer.parseInt(others_id));
    }
}
