package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class AppConfig implements  WebMvcConfigurer{

    @Autowired
    ApiInterseptor apiInterseptor;

    @Override
    public void addInterceptors (InterceptorRegistry registry){
        registry.addInterceptor(apiInterseptor).addPathPatterns("/api/v1/employee");
    }
}
