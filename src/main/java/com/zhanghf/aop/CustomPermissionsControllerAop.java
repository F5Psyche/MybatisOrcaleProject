package com.zhanghf.aop;

import com.zhanghf.dto.CommonDTO;
import com.zhanghf.enums.BusinessCodeEnum;
import com.zhanghf.util.CommonUtils;
import com.zhanghf.util.HttpServletRequestUtils;
import com.zhanghf.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

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

    @Around("@annotation(com.zhanghf.annotation.CustomPermissionsController)")
    public Object customPermissionsControllerAround(ProceedingJoinPoint jointPoint) {
        String uuid = UUID.randomUUID().toString();
        ResultVO<Map<String, Object>> resultVo = new ResultVO<>(uuid);
        HttpServletRequest request = HttpServletRequestUtils.getHttpServletRequest();
        String ssoToken = request.getHeader("ssoToken");
        try {
            int length = ssoToken.length();
            return jointPoint.proceed();
        } catch (Throwable throwable) {
            log.error(CommonDTO.COMMON_LOGGER_ERROR_INFO_PARAM, uuid, CommonUtils.getStackTraceString(throwable));
            resultVo.setResultDes("CustomPermissionsController异常");
            resultVo.setCode(BusinessCodeEnum.UNKNOWN_ERROR.getCode());
            return resultVo;
        }
    }
}
