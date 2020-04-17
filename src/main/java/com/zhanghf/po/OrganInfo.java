package com.zhanghf.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhanghf
 * @version 1.0
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganInfo implements Serializable {
    private String organId;

    private String organCode;

    private String organName;

    private String cityInfo;

    private String countyInfo;

    private String areaCode;

    private String extInfo;
}
