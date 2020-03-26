package com.zhanghf.exception;

/**
 * @author zhanghf
 * @version 1.0
 * @date 15:58 2020/3/25
 */
public class CustomBusinessException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CustomBusinessException(){

    }

    public CustomBusinessException(String str){
        super(str);
    }

    public CustomBusinessException(Throwable throwable){
        super(throwable);
    }

    public CustomBusinessException(String str, Throwable throwable){
        super(str, throwable);
    }
}
