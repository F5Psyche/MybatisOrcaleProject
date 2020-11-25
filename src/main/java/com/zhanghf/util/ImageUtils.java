package com.zhanghf.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author zhanghf
 * @version 1.0
 * @date 16:57 2020/6/19
 */
@Slf4j
public class ImageUtils {

    private ImageUtils() {
        throw new IllegalStateException("ImageUtils");
    }

    public static void imageDownloadWithUrl(String uuid, String httpUrl, File file) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file)
        ) {
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            InputStream inputStream = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int len;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.close();
            byte[] bytes = byteArrayOutputStream.toByteArray();
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            log.error("uuid={}, errMsg={}", uuid, CommonUtils.exceptionToString(e));
        }

    }

    public static void imageDownloadWithCode(String uuid, String base64Code, File file) {
        byte[] bytes = Base64.decodeBase64(base64Code);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(bytes);
        } catch (Exception e) {
            log.error("uuid={}, errMsg={}", uuid, CommonUtils.exceptionToString(e));
        }
    }

    public static FileInputStream byteToFile(String uuid, String base64Code, String path, String eInvoiceNumber, String type) {
        if (base64Code != null) {
            File filePath = new File(path);
            //判断目录是否存在,不存在即创建
            boolean flag = filePath.exists() || filePath.mkdirs();
            if (flag) {
                File file = new File(eInvoiceNumber + type);
                byte[] bytes = Base64.decodeBase64(base64Code);

                try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                    fileOutputStream.write(bytes);
                } catch (Exception e) {
                    log.error("uuid={}, errMsg={}", uuid, CommonUtils.exceptionToString(e));
                }

                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    return fileInputStream;
                } catch (IOException e) {
                    log.error("uuid={}, errMsg={}", uuid, CommonUtils.exceptionToString(e));
                } finally {
                    if (file.exists()) {
                        file.delete();
                    }
                }

            }

        }
        return null;
    }
}
