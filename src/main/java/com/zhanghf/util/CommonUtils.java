package com.zhanghf.util;

import com.zhanghf.dto.CommonDTO;
import com.zhanghf.enums.BusinessCodeEnum;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:09 2020/3/12
 */
@Slf4j
public class CommonUtils {

    private CommonUtils() {
        throw new IllegalStateException("CommonUtils");
    }

    /**
     * 将异常信息转换为字符串
     *
     * @param e 异常信息
     * @return 字符串
     */
    public static String exceptionToString(Exception e) {
        try (
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw)
        ) {
            e.printStackTrace(pw);
            return sw.toString();
        } catch (Exception ex) {
            return "ExceptionToString is error";
        }
    }

    /**
     * 将内容写入
     *
     * @param uuid     唯一识别码
     * @param content  内容
     * @param fileName 文件名
     * @return 结果
     */
    public static ResultVo<String> writeFile(String uuid, String content, String fileName) {
        ResultVo<String> resultVo = new ResultVo<>();
        String pathName = "\\BlockChain\\" + fileName;
        File file = new File(pathName);
        if (!file.getParentFile().exists()) {
            boolean flag = file.getParentFile().mkdirs();
            log.info("uuid={}, flag={}", uuid, flag);
        }
        try (
                OutputStream outputStream = new FileOutputStream(file)
        ) {
            byte[] bytes = content.getBytes();
            outputStream.write(bytes);
            resultVo.setSuccess(true);
        } catch (IOException e) {
            resultVo.setResult("");
            resultVo.setCode("8099");
            resultVo.setResultDes(e.toString());
            log.error("<writeFile.IOException>uuid={}, errMsg={}", uuid, exceptionToString(e));
        }
        return resultVo;
    }

    /**
     * 读取文件内容
     *
     * @param uuid     唯一识别码
     * @param fileName 文件名
     * @return 结果
     */
    public static ResultVo<String> readFile(String uuid, String fileName) {
        ResultVo<String> resultVo = new ResultVo<>();
        String pathName = "\\BlockChain\\" + fileName;
        File file = new File(pathName);
        try {
            InputStream inputStream = new FileInputStream(file);
            resultVo = inputStreamToString(uuid, inputStream);
            if (StringUtils.isEmpty(resultVo.getResult())) {
                resultVo.setSuccess(false);
            }
        } catch (FileNotFoundException e) {
            resultVo.setResult("");
            resultVo.setSuccess(false);
            resultVo.setCode("8099");
            resultVo.setResultDes(e.toString());
            log.error("<readFile.FileNotFoundException>uuid={}, errMsg={}", uuid, exceptionToString(e));
        }
        return resultVo;
    }

    /**
     * @param uuid        唯一识别码
     * @param inputStream InputStream
     * @return ResultVo
     */
    public static ResultVo<String> inputStreamToString(String uuid, InputStream inputStream) {
        ResultVo<String> resultVo = new ResultVo<>();
        StringBuilder buffer = new StringBuilder();
        try (
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                buffer.append(lines);
            }
            resultVo.setResult(buffer.toString());
            resultVo.setSuccess(true);
        } catch (Exception e) {
            resultVo.setResult("");
            resultVo.setCode("8099");
            resultVo.setResultDes(e.toString());
            log.error("<inputStreamToString.Exception>uuid={}, errMsg={}", uuid, exceptionToString(e));
        }
        return resultVo;
    }

    /**
     * 获取系统时间
     *
     * @param pattern           时间格式
     * @param currentTimeMillis 当前时间
     * @return 返回时间
     */
    public static String getTime(String pattern, Long currentTimeMillis) {
        Date date;
        if (StringUtils.isEmpty(currentTimeMillis)) {
            date = new Date();
        } else {
            date = new Date(currentTimeMillis);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static void downloadFile(String base64Code, File file) {
        try (
                FileOutputStream fos = new FileOutputStream(file)
        ) {
            byte[] bytes = Base64.decodeBase64(base64Code);
            fos.write(bytes);
        } catch (Exception e) {
            log.error("errMsg={}", exceptionToString(e));
        }
    }

    /**
     * 封装异常信息的错误
     *
     * @param uuid 唯一识别码
     * @param e    异常信息
     * @param <T>  泛型
     * @return 封装结果
     */
    public static <T> ResultVo<T> getExceptionResult(String uuid, Exception e) {
        String errMsg = exceptionToString(e);
        log.error(CommonDTO.COMMON_LOGGER_ERROR_INFO_PARAM, uuid, errMsg);
        return BusinessCodeEnum.getMsgCode(uuid, errMsg);
    }

}
