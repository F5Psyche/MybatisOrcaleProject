package com.zhanghf.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhanghf
 * @version 1.0
 * @date 12:15 2020/2/17
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtMatterInfo implements Serializable {

    private String localInnerCode;

    private String matName;

    private String matCode;

    private String matKind;

    private String serviceCode;

    private String serviceCodeId;

    private String matNote;

    private String matType;

    private String hndlCstr;

    private String matSettingBasis;

    private String srvrObj;

    private String leadDept;

    private String appointKind;

    private String statutoryDeadline;

    private String statutoryDeadlineUnit;

    private String consultMethod;

    private String superviseMethod;

    private String outProcess;

    private String matCommonName;

    private String entityName;

    private String ouGuid;

    private String acceptAddress;

    private String jurisCode;

    private Date gmtCreate;

    private String userCreate;

    private Date gmtModified;

    private String userModified;

    private String extInfo;


}