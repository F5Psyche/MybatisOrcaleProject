package com.zhanghf.vo.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhanghf
 * @version 1.0
 * @date 9:25 2020/12/25
 */
@Data
public class LoginInfoVO implements Serializable {

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("用户姓名")
    private String userName;

    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("登录密码")
    private String loginPassword;

    @ApiModelProperty("登录时间")
    private String loginTime;

    @ApiModelProperty("机构编码")
    private String organCode;

    @ApiModelProperty("角色类型")
    private String roleType;
}
