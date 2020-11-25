package com.zhanghf.controller;

import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhanghf
 * @version 1.0
 * @date 16:20 2020/8/12
 */
@Slf4j
@RestController
@RequestMapping("")
public class SessionCookieController {

    @RequestMapping("/session")
    public ResultVo<List<Map<String, Object>>> getSessionCookieController(HttpServletRequest request, HttpServletResponse response) {
        ResultVo<List<Map<String, Object>>> resultVo = new ResultVo<>();
        Map<String, Object> map = new HashMap<>(16);
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        map.put("session", session);
        map.put("cookie", cookies);
        log.info("map={}", map);
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
        log.info("list={}", list);
        resultVo.setResult(new ArrayList<>());
        return resultVo;
    }
}
