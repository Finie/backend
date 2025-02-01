package com.spring.backend.config;

import com.spring.backend.middleware.ApiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptWebMvcConfigurer implements WebMvcConfigurer {

    /**
     * This file class helps add
     * the custom middleware that checks
     * for api key presence in any request
     * with /api/ path to the WebMvcConfigurer
     * */

    @Autowired
    private ApiInterceptor apiInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor)
                .addPathPatterns("/api/**");
    }


}
