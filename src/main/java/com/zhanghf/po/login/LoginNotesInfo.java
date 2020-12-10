package com.zhanghf.po.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhanghf
 * @version 1.0
 * @date 15:33 2020/12/7
 */
@Data
public class LoginNotesInfo implements Serializable {
    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("登录密码")
    private String loginPassword;

    @ApiModelProperty("登录时间")
    private Date gmtCreate;

    @ApiModelProperty("失效时间")
    private Date gmtInvalid;

    @ApiModelProperty("base64")
    private String baseCode;

    @ApiModelProperty("ssoToken")
    private String ssoToken;

    @ApiModelProperty("是否失效（0.生效；1.失效）")
    private String isInvalid;
}
