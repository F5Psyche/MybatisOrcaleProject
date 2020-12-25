package com.zhanghf.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhanghf.dto.CommonDTO;
import com.zhanghf.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 加密工具类
 *
 * @author zhanghf
 * @version 1.0
 * @date 9:07 2020/3/11
 */
@Slf4j
public class HttpConnectionUtils {

    private HttpConnectionUtils() {
        throw new IllegalStateException("HttpConnectionUtils");
    }

    /**
     * @param uuid 唯一识别码
     * @param uri  请求地址
     * @param json 请求参数
     * @return 返回参数
     */
    public static Object httpConnectionPost(String uuid, String uri, JSONObject json) {
        ResultVO<String> resultVo = new ResultVO<>();
        int status = 0;
        String message = "";
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty(CommonDTO.HEADER_NAME, CommonDTO.HEADER_VALUE);
            connection.connect();
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(json.toJSONString().getBytes(StandardCharsets.UTF_8));
            InputStream inputStream = connection.getInputStream();
            resultVo = CommonUtils.inputStreamToString(uuid, inputStream);
            status = connection.getResponseCode();
            message = connection.getResponseMessage();
            connection.disconnect();
        } catch (IOException e) {
            log.error("uuid={}, status={}, message={}, resultVo={}, errMsg={}", uuid, status, message, resultVo, e.toString());
            return e.toString();
        }
        log.info("uuid={}, status={}, message={}, resultVo={}", uuid, status, message, resultVo);
        try {
            return JSON.parse(resultVo.getResult());
        } catch (Exception e) {
            return resultVo.getResult();
        }
    }


    /**
     * @param uuid      唯一识别码
     * @param httpUrl   请求地址
     * @param condition 请求参数
     * @return 返回参数
     */
    public static Object httpClientPost(String uuid, String httpUrl, JSONObject condition) {
        HttpPost post = new HttpPost(httpUrl);
        List<NameValuePair> list = new ArrayList<>();
        try {
            for (String key : condition.keySet()) {
                Object object = condition.get(key);
                String simpleName = object.getClass().getSimpleName();
                switch (simpleName) {
                    case "String":
                        list.add(new BasicNameValuePair(key, condition.getString(key)));
                        break;
                    case "JSONArray":
                        JSONArray array = condition.getJSONArray(key);
                        for (Object obj : array) {
                            list.add(new BasicNameValuePair(key, obj.toString()));
                        }
                        break;
                    default:
                        log.error("uuid={}, simpleName={}, object={}", uuid, simpleName, object);
                        list.add(new BasicNameValuePair(key, object.toString()));
                        break;
                }
            }
            log.info("uuid={}, list={}", uuid, list);
            post.setEntity(new UrlEncodedFormEntity(list, CommonDTO.CHARSET_NAME));
        } catch (Exception e) {
            log.error("uuid={}, errMsg={}", uuid, e.toString());
            return e.toString();
        }
        return httpPostUsing(uuid, post);
    }

    /**
     * @param uuid    唯一识别码
     * @param httpUrl 请求地址
     * @param object  请求参数
     * @param header  请求头
     * @return 返回参数
     */
    public static Object httpClientPost(String uuid, String httpUrl, Object object, JSONObject header) {
        HttpPost post = new HttpPost(httpUrl);
        post.setHeader(CommonDTO.HEADER_NAME, CommonDTO.HEADER_VALUE);
        if (!CollectionUtils.isEmpty(header)) {
            header.keySet().forEach(key -> {
                post.setHeader(key, header.getString(key));
            });
        }
        byte[] bytes = object.toString().getBytes(StandardCharsets.UTF_8);
        post.setEntity(new ByteArrayEntity(bytes));
        return httpPostUsing(uuid, post);
    }


    /**
     * @param uuid 唯一识别码
     * @param post httpPost
     * @return 返回参数
     */
    private static String httpPostUsing(String uuid, HttpPost post) {
        int statusOk = 200;
        post.setConfig(CommonDTO.REQUEST_TIMEOUT_CONFIG);
        try (
                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(post)
        ) {
            // http状态码
            int status = response.getStatusLine().getStatusCode();
            if (status != statusOk) {
                log.error(CommonDTO.COMMON_LOGGER_ERROR_INFO_PARAM, uuid, "httpStatus<" + status + ">请求错误");
                throw new IllegalArgumentException("httpPost请求异常");
            }
            HttpEntity entity = response.getEntity();
            String responseContent = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            log.info("uuid={}, status={}, responseContent={}", uuid, status, responseContent);
            return responseContent;
        } catch (Exception e) {
            log.error(CommonDTO.COMMON_LOGGER_ERROR_INFO_PARAM, uuid, CommonUtils.getStackTraceString(e));
            throw new IllegalArgumentException("httpPost请求异常");
        }
    }
}
