package com.zhanghf.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhanghf.annotation.CustomPermissionsService;
import com.zhanghf.enums.BusinessCodeEnum;
import com.zhanghf.util.HttpServletRequestUtils;
import com.zhanghf.vo.ResultVo;
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
import java.lang.reflect.Parameter;

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
     */
    @Around("customPermissionsServicePointCut()")
    public Object customPermissionsServiceAround(ProceedingJoinPoint jointPoint) {
        //获取方法的入参
        Object[] objects = jointPoint.getArgs();
        String uuid = objects[0].toString();
        ResultVo resultVo = new ResultVo(uuid);
        try {
            JSONArray array = JSON.parseArray(JSON.toJSONString(objects));
            log.info("array={}", array);
            //获得当前访问的类(class)
            Class<?> clazz = jointPoint.getTarget().getClass();
            //获取目前方法的签名
            Signature signature = jointPoint.getSignature();
            //获得访问的方法名
            String methodName = signature.getName();
            MethodSignature methodSignature = (MethodSignature) signature;
            //得到方法的参数的类型
            Class[] argClass = methodSignature.getParameterTypes();
            Method method = clazz.getDeclaredMethod(methodName, argClass);
            String[] parameterNames = methodSignature.getParameterNames();
            Parameter[] parameters = method.getParameters();
            log.info("uuid={}, clazz={}, signature={}, methodName={}, argClass={}, method={}", uuid, clazz, signature, methodName, argClass, method);
            log.info("uuid={}, parameterNames={}, parameters={}", uuid, parameterNames, parameters);
            CustomPermissionsService customPermissionsService = method.getAnnotation(CustomPermissionsService.class);
            String role = customPermissionsService.role().getRole();
            log.info("uuid={}, customPermissionsService={}, role={}", uuid, customPermissionsService, role);

            //获取请求信息
            ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
            if (servletRequestAttributes != null) {
                //request.getInputStream()是获取InputStream对象,InputStream的read()方法内部有一个postion。读取完后想要再次读取需要reset。reset有条件,具体再百度进行学习
                HttpServletRequest request = servletRequestAttributes.getRequest();
                log.info("uuid={}, params={}, ssToken={}", uuid, HttpServletRequestUtils.getParameter(uuid, request), request.getHeader("ssToken"));
            }
            return jointPoint.proceed();
        } catch (Throwable throwable) {
            resultVo.setCode(BusinessCodeEnum.UNKNOWN_ERROR.getCode());
            resultVo.setResultDes(BusinessCodeEnum.UNKNOWN_ERROR.getMsg());
            log.error("uuid={}, errMsg={}", uuid, throwable.getMessage());
        }
        return resultVo;
    }
}
