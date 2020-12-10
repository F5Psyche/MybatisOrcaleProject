package com.zhanghf.util;

import org.apache.commons.codec.binary.Base64;

/**
 * @author zhanghf
 * @version 1.0
 * @date 15:11 2020/12/7
 */
public class Base64Utils {

    /**
     * 将字符串转换为base64Code
     *
     * @param inputText 字符串
     * @return base64Code
     */
    public static String base64Code(String inputText) {
        Base64 base64 = new Base64();
        byte[] bytes = inputText.getBytes();
        return base64.encodeToString(bytes);
    }

    /**
     * @param base64Code
     * @return
     */
    public static String base64CodeToString(String base64Code) {
        Base64 base64 = new Base64();
        byte[] bytes = base64.decode(base64Code);
        return new String(bytes);
    }
}
