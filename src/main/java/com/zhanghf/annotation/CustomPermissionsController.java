package com.zhanghf.annotation;

import com.zhanghf.enums.RoleEnum;

import java.lang.annotation.*;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:32 2020/4/16
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomPermissionsController {

    RoleEnum role();
}
