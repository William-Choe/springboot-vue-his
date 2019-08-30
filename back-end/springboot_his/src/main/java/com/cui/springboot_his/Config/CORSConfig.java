package com.cui.springboot_his.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static org.springframework.web.cors.CorsConfiguration.ALL;

/**
 * CORS配置：跨域资源共享
 */
@Configuration
public class CORSConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(ALL)
                .allowedMethods(ALL)
                .allowedHeaders(ALL)
                .allowCredentials(true);
    }
}
