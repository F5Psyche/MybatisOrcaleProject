package com.zhanghf.controller.session;

import com.zhanghf.dto.CommonDTO;
import com.zhanghf.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author zhanghf
 * @version 1.0
 * @date 16:20 2020/8/12
 */
@Slf4j
@RestController
@RequestMapping("session")
public class SessionCookieController {

    @RequestMapping("/cookie")
    public void getSessionCookie(HttpServletRequest request, HttpServletResponse response) {
        String uuid = UUID.randomUUID().toString();
        //设置contentType编码，获得cookies对象
        response.setContentType("text/html;charset=utf-8");
        try {
            Cookie[] cookies = request.getCookies();
            //定义标识位
            boolean flag = true;
            if (cookies != null && cookies.length != 0) {
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if ("lastTime".equals(name)) {
                        flag = false;
                        //获取lastTime的value值，URLDecoder解码后显示在页面上
                        String value = cookie.getValue();
                        System.out.println("解码前:" + value);
                        value = URLDecoder.decode(value, "utf-8");
                        System.out.println("解码后:" + value);
                        response.getWriter().println("<h4>欢迎回来，你上次访问时间是:</h4>" + value);

                        //修改cookie的新值
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                        String str_date = simpleDateFormat.format(new Date());
                        System.out.println("编码前:" + str_date);
                        str_date = URLEncoder.encode(str_date, "utf-8");
                        System.out.println("编码后:" + str_date);
                        //修改cookie的值和生命周期，发送cookie
                        cookie.setValue(str_date);
                        cookie.setMaxAge(30);
                        response.addCookie(cookie);

                    }
                }
            }

            if (cookies == null || cookies.length == 0 || flag) {
                response.getWriter().println("当前你是首次访问页面..");
                //创建cookie对象，并且将cookie保存到客户端
                Cookie cookie = new Cookie("lastTime", "");
                //时间格式化
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                String str_date = simpleDateFormat.format(new Date());

                System.out.println("编码前:" + str_date);
                //对str进行URLEncoder编码，因为cookie的value不可以有特殊字符，比如空格
                str_date = URLEncoder.encode(str_date, CommonDTO.CHARSET_NAME);
                System.out.println("编码后:" + str_date);
                //修改cookie的值和生命周期，发送cookie
                cookie.setValue(str_date);
                //单位秒
                cookie.setMaxAge(30);
                response.addCookie(cookie);
            }
        } catch (IOException e) {
            log.error("errMsg={}", CommonUtils.getStackTraceString(e));
        }
    }


}
