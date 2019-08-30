package com.cui.springboot_his.Service;

import com.cui.springboot_his.Model.ResultMap;


public interface BackRegistrationService {
    ResultMap findRegistrationForm(Integer registrationForm_id);

    ResultMap backRegistrate(Integer registrationForm_id);
}
