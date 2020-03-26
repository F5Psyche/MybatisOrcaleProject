package com.zhanghf.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhanghf
 * @version 1.0
 * @date 18:20 2020/3/24
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatterProcessMenuVO implements Serializable {

    private String systemId;

    private String systemName;

    private String ouGuid;

    private String areaCode;

    private List<MenuValueVO> menuValueList;

    private List<MatterProcessMenuVO> children;

}
