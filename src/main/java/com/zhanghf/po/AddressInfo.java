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
public class AddressInfo {

    private Long id;

    private String localInnerCode;

    private String address;

    private String addressKind;

    private String acceptTimeDesc;

    private String phone;

    private String uuid;

    private String isInner;

}