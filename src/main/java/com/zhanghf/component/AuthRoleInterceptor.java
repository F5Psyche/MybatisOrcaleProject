package com.zhanghf.component;

import com.alibaba.fastjson.JSON;
import com.zhanghf.annotation.RoleNum;
import com.zhanghf.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.UUID;

/**
 * 自定义注解RoleNum的实现层
 *
 * @author zhanghf
 * @version 1.0
 * @date 16:33 2020/4/15
 */
@Slf4j
@Component
public class AuthRoleInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uuid = UUID.randomUUID().toString();
        ResultVO<Map<String, Object>> resultVo = new ResultVO<>(uuid);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RoleNum roleNum = handlerMethod.getMethodAnnotation(RoleNum.class);
            log.info("uuid={}, roleNum={}, method={}, methodName={}", uuid, roleNum, method, method.getName());
            log.info("url={}, uri={}, path={}, flag={}", request.getRequestURL(), request.getRequestURI(), request.getServletPath(), !"/error".equals(request.getServletPath()));
            if (!"/error".equals(request.getServletPath()) && roleNum == null) {
                return true;
            }
        }
        response.getWriter().append(JSON.toJSONString(resultVo));
        return false;
    }

}
