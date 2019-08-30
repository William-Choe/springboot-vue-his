package com.cui.springboot_his.Controller;

import com.cui.springboot_his.Model.ResultMap;
import com.cui.springboot_his.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 返回登录状态
     */
    @PostMapping(name = "/login")
    public ResultMap login(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        return loginService.login(username, password);
    }

    /**
     * 根据用户ID获取医生ID
     * @param user_id 用户ID
     * @return 返回医生ID
     */
    @PostMapping(value = "/login/get-doctor-id-by-user")
    public ResultMap getDoctorByUser(@RequestParam("user_id") String user_id) {
        return loginService.getDoctorIdByUserId(Integer.parseInt(user_id));
    }
}