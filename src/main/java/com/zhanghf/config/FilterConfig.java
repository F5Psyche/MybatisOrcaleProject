package com.zhanghf.config;

import com.zhanghf.filter.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:19 2020/11/26
 */
@Slf4j
@Configuration
public class FilterConfig {

    /**
     * setFilter：注册自定义过滤器
     * setName：过滤器名称
     * addUrlPatterns：过滤路径
     * setOrder：优先级,数值越小越先执行(只对doFilter有效，init和destroy无效)
     *
     * @return
     */
    @Bean
    FilterRegistrationBean<HttpServletRequestReplacedFilter> httpServletRequestReplacedFilter() {
        FilterRegistrationBean<HttpServletRequestReplacedFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new HttpServletRequestReplacedFilter());
        bean.setName("httpServletRequestReplacedFilter");
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }

    @Bean
    FilterRegistrationBean<No1Filter> filterNo1() {
        FilterRegistrationBean<No1Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new No1Filter());
        bean.setName("filterNo1");
        bean.addUrlPatterns("/*");
        bean.setOrder(2);
        return bean;
    }

    @Bean
    FilterRegistrationBean<No2Filter> filterNo2() {
        FilterRegistrationBean<No2Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new No2Filter());
        bean.setName("filterNo2");
        bean.addUrlPatterns("/*");
        bean.setOrder(3);
        return bean;
    }

    @Bean
    FilterRegistrationBean<No3Filter> filterNo3() {
        FilterRegistrationBean<No3Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new No3Filter());
        bean.setName("filterNo3");
        bean.addUrlPatterns("/*");
        bean.setOrder(4);
        return bean;
    }

    @Bean
    FilterRegistrationBean<No4Filter> filterNo4() {
        FilterRegistrationBean<No4Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new No4Filter());
        bean.setName("filterNo4");
        bean.addUrlPatterns("/*");
        bean.setOrder(5);
        return bean;
    }

    @Bean
    FilterRegistrationBean<No5Filter> filterNo5() {
        FilterRegistrationBean<No5Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new No5Filter());
        bean.setName("filterNo5");
        bean.addUrlPatterns("/*");
        bean.setOrder(6);
        return bean;
    }
}
