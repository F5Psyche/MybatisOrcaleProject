package com.zhanghf.exception;

/**
 * @author zhanghf
 * @version 1.0
 * @date 16:26 2020/12/25
 */
public class InputParamNullException extends NullPointerException {

    public InputParamNullException() {

    }

    public InputParamNullException(String message) {
        super("参数<" + message + ">不能为空");
    }
}
