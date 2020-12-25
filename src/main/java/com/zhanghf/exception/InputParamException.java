package com.zhanghf.exception;

/**
 * @author zhanghf
 * @version 1.0
 * @date 16:26 2020/12/25
 */
public class InputParamException extends RuntimeException {

    public InputParamException() {

    }

    public InputParamException(String message) {
        super("参数<" + message + ">内容不符合规范");
    }
}
