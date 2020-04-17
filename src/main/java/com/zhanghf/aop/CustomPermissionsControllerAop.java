package com.zhanghf.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Controller层自定义注解拦截
 * 自定义注解CustomPermissionsController的实现层
 * <p>@Order 控制多个Aspect的执行顺序，越小越先执行
 *
 * @author zhanghf
 * @version 1.0
 * @date 10:28 2020/4/16
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class CustomPermissionsControllerAop {
}
