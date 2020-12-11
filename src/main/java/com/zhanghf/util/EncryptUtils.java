package com.zhanghf.util;

import com.zhanghf.dto.CommonDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 *
 * @author zhanghf
 * @version 1.0
 * @date 9:07 2020/2/6
 */
@Slf4j
public class EncryptUtils {

    private EncryptUtils() {
        throw new IllegalStateException("EncryptUtils");
    }

    /**
     * sha-256加密
     *
     * @param inputData 需加密数据
     * @return 加密数据
     */
    public static String sha256(final String inputData) {
        String encryptionType = "SHA-256";
        return sha(inputData, encryptionType);
    }

    /**
     * sha-512加密
     *
     * @param inputData 需加密数据
     * @return 加密数据
     */
    public static String sha512(final String inputData) {
        String encryptionType = "SHA-512";
        return sha(inputData, encryptionType);
    }

    /**
     * MD5加密
     *
     * @param inputData 需加密数据
     * @return 加密数据
     */
    public static String md5(final String inputData) {
        String encryptionType = "MD5";
        return sha(inputData, encryptionType);
    }

    /**
     * 通用加密方法
     *
     * @param inputData      需加密的数据
     * @param encryptionType 加密类型（SHA-256,SHA-512,MD5）
     * @return 加密数据
     */
    private static String sha(final String inputData, final String encryptionType) {
        String encryptionData = null;
        if (inputData != null && inputData.length() > 0) {
            try {
                //SHA 加密开始
                //创建加密对象,传入加密类型
                MessageDigest messageDigest = MessageDigest.getInstance(encryptionType);
                //传入要加密的字符串
                messageDigest.update(inputData.getBytes(CommonDTO.CHARSET_NAME));
                //得到byte数组
                byte[] byteBuffer = messageDigest.digest();
                //将byte数组转成String类型
                StringBuilder builder = new StringBuilder();
                //遍历byte数组
                int size = byteBuffer.length;
                for (int i = 0; i < size; i++) {
                    //转换成16进制并存储在字符串中
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    //如果结果小于16,则在前面加上一个0填满两位的十六进制
                    if (hex.length() == 1) {
                        builder.append("0");
                    }
                    builder.append(hex);
                }
                encryptionData = builder.toString();
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return encryptionData;
    }
}
