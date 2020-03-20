package com.zhanghf.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:09 2020/3/12
 */
@Slf4j
public class HttpServletRequestUtils {

    private HttpServletRequestUtils() {
        throw new IllegalStateException("HttpServletRequestUtils");
    }

    /**
     * 获取参数
     *
     * @param uuid    唯一码
     * @param request 请求内容
     * @return 请求参数
     */
    public static JSONObject getParameter(String uuid, HttpServletRequest request) {
        String httpUrl = request.getRequestURL() + (StringUtils.isEmpty(request.getQueryString()) ? "" : ("?" + request.getQueryString()));
        JSONObject result = new JSONObject();
        String inputLine;
        StringBuilder buffer = new StringBuilder();
        try (
                InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            while ((inputLine = bufferedReader.readLine()) != null) {
                buffer.append(inputLine);
            }
            String data = buffer.toString();
            boolean flag = StringUtils.isEmpty(data);
            if (!flag) {
                result = JSONObject.parseObject(data);
            }
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                result.put(paramName, request.getParameter(paramName));
            }
            log.info("uuid={}, flag={}, method={}, condition={}", uuid, flag, request.getMethod(), result);
        } catch (Exception e) {
            log.error("uuid={}, flag={}, errMsg={}", uuid, StringUtils.isEmpty(buffer.toString()), e.toString());
        }
        return result;
    }

    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
