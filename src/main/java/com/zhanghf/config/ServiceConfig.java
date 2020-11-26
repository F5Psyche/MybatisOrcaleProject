package com.zhanghf.config;

import com.zhanghf.util.HttpServletRequestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhanghf
 * @version 1.0
 * @date 19:22 2020/2/6
 */
@Configuration
public class ServiceConfig {

    @Bean(name = "httpServletRequestUtils")
    HttpServletRequestUtils httpServletRequestUtils() {
        HttpServletRequestUtils httpServletRequestUtils = new HttpServletRequestUtils();
        return httpServletRequestUtils;
    }

}
