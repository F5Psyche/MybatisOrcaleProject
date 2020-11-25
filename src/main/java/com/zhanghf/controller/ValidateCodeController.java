package com.zhanghf.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:19 2020/11/5
 */
@Slf4j
@RestController
@RequestMapping("")
public class ValidateCodeController {

    @GetMapping(value = "/vCode")
    public void vCodeShow(HttpServletRequest request, HttpServletResponse response) {

    }
}
