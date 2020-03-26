package com.zhanghf.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:09 2020/2/17
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InnerMatterInfo {


    private String matId;


    private String localInnerCode;

    private String matName;


    private String matCode;


    private Long matKind;


    private String serviceCode;


    private String serviceCodeId;


    private String isStatus;


    private String isBlockchain;


    private String matNote;


    private String matType;


    private String hndlCstr;


    private String matSettingBasis;


    private String srvrObj;


    private String leadDept;


    private String hndlKind;


    private String appointKind;


    private String statutoryDeadline;


    private String statutoryDeadlineUnit;


    private String consultMethod;


    private String superviseMethod;


    private String acptCond;


    private String outProcess;


    private String chargeStandard;


    private String chargeBasis;


    private String matCommonName;


    private String entityName;


    private String ouGuid;


    private String isProv;


    private String isRepeat;


    private String otMatAllow;


    private String jurisCode;


    private Date gmtCreate;


    private String userCreate;


    private Date gmtModified;


    private String userModified;


    private String extInfo;

    public InnerMatterInfo(String matId, String userModified) {
        this.matId = matId;
        this.userModified = userModified;
    }
}