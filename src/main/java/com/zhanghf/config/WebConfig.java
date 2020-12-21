package com.zhanghf.config;

import com.zhanghf.component.AuthRoleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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

    /**
     * /** (匹配所有路径)
     * /admin/** (匹配 /admin/ 下的所有路径)
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authRoleInterceptor)
                //拦截
                .addPathPatterns("/**")
                //排查拦截
                .excludePathPatterns("/", "/*.html", "/swagger-ui.html");
    }

}
