package com.zhanghf.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhanghf
 * @version 1.0
 * @date 18:45 2020/3/24
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuValueVO implements Serializable {

    private String menuId;

    private String menuName;

    private String menuUrl;

    private String channelType;
}
