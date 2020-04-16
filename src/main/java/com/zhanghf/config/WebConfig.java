package com.zhanghf.config;

import com.zhanghf.component.AuthRoleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author zhanghf
 * @version 1.0
 * @date 16:36 2020/4/15
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    AuthRoleInterceptor authRoleInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authRoleInterceptor);
    }
}
