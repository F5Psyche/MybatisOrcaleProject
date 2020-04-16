package com.zhanghf.aop;

import com.zhanghf.annotation.CustomPermissionsService;
import com.zhanghf.util.HttpServletRequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Service层自定义注解拦截
 * 自定义注解CustomPermissionsService的实现层
 * <p>@Order 控制多个Aspect的执行顺序，越小越先执行
 *
 * @author zhanghf
 * @version 1.0
 * @date 9:20 2020/4/16
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class CustomPermissionsServiceAop {


    @Pointcut("@annotation(com.zhanghf.annotation.CustomPermissionsService)")
    public void customPermissionsServicePointCut() {

    }

    /**
     * @param jointPoint 连接点,拦截点
     * @return jointPoint.proceed()放行, 执行业务逻辑;
     * @throws Throwable
     */
    @Around("customPermissionsServicePointCut()")
    public Object customPermissionsServiceAround(ProceedingJoinPoint jointPoint) throws Throwable {

        //获得当前访问的class
        Class<?> clazz = jointPoint.getTarget().getClass();
        //获得访问的方法名
        Signature signature = jointPoint.getSignature();
        String methodName = signature.getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature) signature).getParameterTypes();
        Method method = clazz.getDeclaredMethod(methodName, argClass);
        CustomPermissionsService customPermissionsService = method.getAnnotation(CustomPermissionsService.class);
        String role = customPermissionsService.role().getRole();
        log.info("clazz={}, methodName={}, argClass={}, method={}, customPermissionsService={}, role={}", clazz, methodName, argClass, method, customPermissionsService, role);

        //获取请求信息
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (servletRequestAttributes == null) {
            return "没有获取到请求信息";
        } else {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            String uuid = UUID.randomUUID().toString();
            log.info("uuid={}, params={}", uuid, HttpServletRequestUtils.getParameter(uuid, request));
        }

        return jointPoint.proceed();
    }
}
