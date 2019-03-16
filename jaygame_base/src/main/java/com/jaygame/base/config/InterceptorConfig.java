package com.jaygame.base.config;

import com.jaygame.base.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * extends WebMvcConfigurationSupport to add Interceptor to Spring Boot
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtInterceptor jwtInterceptor;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //must register JwtInterceptor and request path.
         registry.addInterceptor(jwtInterceptor)
                 .addPathPatterns("/**")
                 .excludePathPatterns("/**/user/login/**");
    }
}
