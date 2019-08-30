package com.cui.springboot_his.Service;

import com.cui.springboot_his.Model.ResultMap;

public interface LoginService {
    ResultMap login(String username, String password);

    ResultMap getDoctorIdByUserId(Integer user_id);
}
