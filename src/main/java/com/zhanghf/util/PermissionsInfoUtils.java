package com.zhanghf.util;

import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author zhanghf
 * @version 1.0
 * @date 12:36 2020/4/16
 */
@Slf4j
public class PermissionsInfoUtils {

    /**
     * @param uuid   唯一识别码
     * @param prefix 扫描路径
     * @return
     */
    public static ResultVo getPermissionsInfo(String uuid, String prefix) {
        ResultVo resultVo = new ResultVo(uuid);

        //扫描com.zhanghf.component.Scheduler,扫描路径不可忘记,不然会扫描全部项目包,包括引用的jar
        Reflections reflections = new Reflections(prefix);
        //获取带Component注解的类
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Component.class);
        for (Class clazz : typesAnnotatedWith) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                //获取该方法上的所有注解
                Annotation[] annotations = method.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    log.info("uuid={}, methodName={}, annotation={}", uuid, methodName, annotation);
                }

            }
        }

        return resultVo;
    }
}
