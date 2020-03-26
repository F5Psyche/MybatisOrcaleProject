package com.zhanghf.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhanghf
 * @version 1.0
 * @date 12:15 2020/2/17
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class InnerMatterUserInfo {

    private Long id;

    private String matId;

    private String userName;

    private String userCard;

    private String userPhone;

    private String userEmail;

    private String jurisCode;

    private String extInfo;


}