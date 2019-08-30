package com.cui.springboot_his.Service.Impl;

import com.cui.springboot_his.Dao.DoctorDao;
import com.cui.springboot_his.Dao.UserDao;
import com.cui.springboot_his.Entity.Doctor;
import com.cui.springboot_his.Entity.User;
import com.cui.springboot_his.Model.ResultMap;
import com.cui.springboot_his.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserDao userDao;
    private final DoctorDao doctorDao;

    @Autowired
    public LoginServiceImpl(UserDao userDao, DoctorDao doctorDao) {
        this.userDao = userDao;
        this.doctorDao = doctorDao;
    }

    @Override
    public ResultMap login(String username, String password) {
        ResultMap resultMap = new ResultMap();

        User user = userDao.findByUsername(username);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                switch (user.getUserType().getType()) {
                    case "admin":
                        resultMap.success().message("admin").data(user.getId());
                        break;
                    case "doctor":
                        resultMap.success().message("doctor").data(user.getId());
                        break;
                    case "operator":
                        resultMap.success().message("operator").data(user.getId());
                        break;
                }
            } else {
                resultMap.fail().message("密码错误！");
            }
        }else {
            resultMap.fail().message("用户名不存在！");
        }
        return resultMap;
    }

    @Override
    public ResultMap getDoctorIdByUserId(Integer user_id) {
        Doctor doctor = doctorDao.findByUser(userDao.findOne(user_id));
        return new ResultMap().success().message("医生ID").data(doctor.getId());
    }
}
