package com.zhanghf.component;

import com.alibaba.fastjson.JSON;
import com.zhanghf.annotation.RoleNum;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 自定义注解RoleNum的实现层
 *
 * @author zhanghf
 * @version 1.0
 * @date 16:33 2020/4/15
 */
@Slf4j
@Service
public class AuthRoleInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uuid = UUID.randomUUID().toString();
        ResultVo resultVo = new ResultVo();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RoleNum roleNum = handlerMethod.getMethodAnnotation(RoleNum.class);
            log.info("uuid={}, roleNum={}", uuid, roleNum);
            if (roleNum == null) {
                return true;
            }
        }
        response.getWriter().append(JSON.toJSONString(resultVo));
        return false;
    }


}
