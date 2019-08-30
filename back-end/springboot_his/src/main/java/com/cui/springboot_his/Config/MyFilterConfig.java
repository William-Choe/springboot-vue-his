package com.cui.springboot_his.Config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * 使用过滤器配置字符串编码
 */
@Configuration
public class MyFilterConfig {
    @Bean
    public FilterRegistrationBean myCharacterEncoding() {
        FilterRegistrationBean myFilter = new FilterRegistrationBean();
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setForceEncoding(true);
        encodingFilter.setEncoding("UTF-8");
        myFilter.setFilter(encodingFilter);
        myFilter.addUrlPatterns("/*");
        return myFilter;
    }
}
