package com.zhanghf.util;

import com.zhanghf.annotation.CheckNull;
import com.zhanghf.exception.CustomBusinessException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zhanghf
 * @version 1.0
 * @date 15:48 2020/3/25
 */
public class CheckNullUtils {

    public static <T> boolean checkNullValidator(T cls) {
        Class<?> clazz = cls.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            CheckNull checkNull = field.getDeclaredAnnotation(CheckNull.class);
            if (null != checkNull) {
                Object value = getValue(cls, field.getName());
                if (!notNull(value)) {
                    throwException(checkNull.message());
                }
            }
        }
        return true;
    }

    public static <T> Object getValue(T cls, String fieldName) {
        Object value = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(cls.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                if (fieldName.equals(descriptor.getName())) {
                    Method method = descriptor.getReadMethod();
                    value = method.invoke(cls, new Object[]{});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static boolean notNull(Object value) {
        if (null == value) {
            return false;
        }
        if (value instanceof String && isEmpty((String) value)) {
            return false;
        }
        if (value instanceof List && isEmpty((List<?>) value)) {
            return false;
        }
        return null != value;
    }

    public static boolean isEmpty(String str) {
        return null == str || str.isEmpty();
    }

    public static boolean isEmpty(List<?> list) {
        return null == list || list.isEmpty();
    }

    private static void throwException(String msg) {
        if (null != msg) {
            throw new CustomBusinessException(msg);
        }
    }
}
