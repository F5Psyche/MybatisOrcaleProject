package com.zhanghf.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghf
 * @version 1.0
 * @date 18:16 2020/2/12
 */
public class MatDicDTO {
    public static Map<String, String> matterMap;

    static {
        matterMap = new HashMap<>();
        matterMap.put("01", "许可");
        matterMap.put("03", "触犯");
        matterMap.put("04", "强制");
        matterMap.put("05", "征收");
        matterMap.put("06", "给付");
        matterMap.put("07", "裁决");
        matterMap.put("08", "确认");
        matterMap.put("09", "行政奖励");
        matterMap.put("10", "其他");
        matterMap.put("13", "审核转报");
        matterMap.put("14", "公共服务");
        matterMap.put("15", "内部管理");
        matterMap.put("16", "联办事项");
    }
}
