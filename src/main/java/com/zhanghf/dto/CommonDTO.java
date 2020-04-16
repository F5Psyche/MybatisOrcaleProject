package com.zhanghf.dto;

import org.apache.http.client.config.RequestConfig;

import java.text.SimpleDateFormat;

/**
 * @author zhanghf
 * @version 1.0
 */
public class CommonDTO {
    public static RequestConfig REQUEST_TIMEOUT_CONFIG = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000)
            .setConnectionRequestTimeout(30000).build();

    public static final String CHARSET_NAME = "UTF-8";
    public static final String HEADER_NAME = "Content-Type";
    public static final String HEADER_VALUE = "application/json";


    public static final String MATTER_SIMPLE_URL = "http://10.85.159.203:10420/mutual/partsCenter/serviceEntrance/8205";
    public static final String MATTER_DETAIL_URL = "http://10.85.159.203:10420/mutual/partsCenter/serviceEntrance/8203";

    public static final int COMMON_INDEX = 0;
    public static final String COMMON_KEY = "result";

    public static SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }
}
