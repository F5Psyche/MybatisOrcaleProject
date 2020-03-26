package com.zhanghf.annotation;

import java.lang.annotation.*;

/**
 * @author zhanghf
 * @version 1.0
 * @Retention(RetentionPolicy.SOURCE) //注解仅存在于源码中，在class字节码文件中不包含
 * @Retention(RetentionPolicy.CLASS) //默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
 * @Retention(RetentionPolicy.RUNTIME) //注解会在class字节码文件中存在，在运行时可以通过反射获取到
 * @Target(ElementType.TYPE) //接口、类
 * @Target(ElementType.FIELD) //属性
 * @Target(ElementType.METHOD) //方法
 * @Target(ElementType.PARAMETER) //方法参数
 * @Target(ElementType.CONSTRUCTOR) //构造函数
 * @Target(ElementType.LOCAL_VARIABLE) //局部变量
 * @Target(ElementType.ANNOTATION_TYPE) //注解
 * @Target(ElementType.PACKAGE) //包
 * @date 15:44 2020/3/25
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckNull {
    String message();
}
