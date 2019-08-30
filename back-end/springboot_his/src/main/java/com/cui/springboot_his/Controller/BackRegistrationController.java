package com.cui.springboot_his.Controller;

import com.cui.springboot_his.Model.ResultMap;
import com.cui.springboot_his.Service.BackRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackRegistrationController {
    private BackRegistrationService backRegistrationService;

    @Autowired
    public BackRegistrationController(BackRegistrationService backRegistrationService) {
        this.backRegistrationService = backRegistrationService;
    }

    /**
     * 查找挂号单
     * @param registrationForm_id 挂号单ID
     * @return 返回挂号单信息
     */
    @PostMapping("/back-registrate/find-registration-form")
    public ResultMap findRegistrationForm(@RequestParam("registrationForm_id") String registrationForm_id) {
        return backRegistrationService.findRegistrationForm(Integer.parseInt(registrationForm_id));
    }

    /**
     * 退号
     * @param registrationForm_id 挂号单ID
     * @return 返回退号成功信息
     */
    @PostMapping("back-registrate")
    public ResultMap backRegistrate(@RequestParam("registrationForm_id") String registrationForm_id) {
        return backRegistrationService.backRegistrate(Integer.parseInt(registrationForm_id));
    }
}
