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
public class InnerMaterialInfo {


    private Long id;


    private String matId;


    private String materialKind;


    private Long materialId;


    private String materialName;


    private String stuffType;


    private String materialCode;


    private String materialSource;


    private String materialForm;


    private String isNeed;


    private Integer paperMaterialNums;


    private Integer imageMaterialNums;


    private String isFile;


    private String extInfo;


}