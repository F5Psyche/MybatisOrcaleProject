package com.zhanghf.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Objects;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:09 2020/3/12
 */
@Slf4j
public class HttpServletRequestUtils {

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
                InputStream inputStream = request.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            while ((inputLine = bufferedReader.readLine()) != null) {
                buffer.append(inputLine);
            }
            String data = buffer.toString();
            boolean flag = StringUtils.isEmpty(data);
            if (!flag) {
                result = JSON.parseObject(data);
            }
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                result.put(paramName, request.getParameter(paramName));
            }
            log.info("uuid={}, flag={}, method={}, httpUrl={}, condition={}", uuid, flag, request.getMethod(), httpUrl, result);
        } catch (Exception e) {
            log.error("uuid={}, flag={}, errMsg={}", uuid, StringUtils.isEmpty(buffer.toString()), e.toString());
        }
        return result;
    }

    /**
     * 获取HttpServletRequest请求
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) Objects.requireNonNull(requestAttributes);
        return servletRequestAttributes.getRequest();
    }


//    /**
//     * 获取参数
//     *
//     * @param uuid    唯一码
//     * @param request 请求内容
//     * @return 请求参数
//     */
//    public static JSONObject getParameter(String uuid, HttpServletRequest request) {
//        JSONObject result = new JSONObject();
//        int num = 0;
//        char ch;
//        StringBuilder buffer = new StringBuilder();
//        try (
//                InputStream inputStream = request.getInputStream();
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
//        ) {
//            while ((num = bufferedReader.read()) != -1) {
//                ch = (char) num;
//                buffer.append(ch);
//            }
//            String data = buffer.toString();
//            boolean flag = StringUtils.isEmpty(data);
//            if (!flag) {
//                result = JSON.parseObject(data);
//            }
//            Enumeration<String> parameterNames = request.getParameterNames();
//            while (parameterNames.hasMoreElements()) {
//                String paramName = parameterNames.nextElement();
//                result.put(paramName, request.getParameter(paramName));
//            }
//            log.info("uuid={}, flag={}, method={}, condition={}", uuid, flag, request.getMethod(), result);
//        } catch (Exception e) {
//            log.error("uuid={}, flag={}, errMsg={}", uuid, StringUtils.isEmpty(buffer.toString()), e.toString());
//        }
//        return result;
//    }


}
