package com.zhanghf.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author zhanghf
 * @version 1.0
 * @date 12:15 2020/2/17
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WfMenuConfigInfo {

    private Long id;

    private Long matProcNodeId;

    private String systemId;

    private String systemName;

    private String menuId;

    private String menuName;

    private String menuUrl;

    private String channelType;

    private String ouGuid;

    private String areaCode;

    private String parentAreaCode;

}