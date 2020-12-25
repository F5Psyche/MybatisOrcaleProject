package com.zhanghf.po.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:22 2020/12/25
 */
@Data
public class UserInfo implements Serializable {

    @Id
    @Column(name = "user_id")
    @ApiModelProperty("用户ID")
    private Integer userId;

    @Column(name = "user_name")
    @ApiModelProperty("用户名")
    private String userName;

    @Column(name = "login_name")
    @ApiModelProperty("登录名")
    private String loginName;

    @Column(name = "login_password")
    @ApiModelProperty("登录密码")
    private String loginPassword;

    @Column(name = "cip_exp_pol")
    @ApiModelProperty("密码过期策略（1.永不过期；2.指定日期过期；3.三个月过期）")
    private String cipExpPol;

    @Column(name = "gmt_cip_exp")
    @ApiModelProperty("密码过期时间")
    private Date gmtCipExp;

    @Column(name = "card_type")
    @ApiModelProperty("证件类型（1.身份证；2.军官证；3.户口本；4.护照）")
    private String cardType;

    @Column(name = "card_no")
    @ApiModelProperty("证件号码")
    private String cardNo;

    @Column(name = "tel_phone")
    @ApiModelProperty("联系电话")
    private String telPhone;

    @Column(name = "user_email")
    @ApiModelProperty("电子邮件")
    private String userEmail;

    @Column(name = "organ_id")
    @ApiModelProperty("机构编码")
    private String organId;

    @Column(name = "organ_code")
    @ApiModelProperty("机构社会信用代码")
    private String organCode;

    @Column(name = "area_code")
    @ApiModelProperty("区域编码")
    private String areaCode;

    @Column(name = "user_type")
    @ApiModelProperty("用户类型")
    private String userType;

    @Column(name = "role_type")
    @ApiModelProperty("角色类型")
    private String roleType;

    @Column(name = "user_exp_pol")
    @ApiModelProperty("用户过期策略（1.永不过期；2.指定日期过期；）")
    private String userExpPol;

    @Column(name = "gmt_user_exp")
    @ApiModelProperty("用户过期时间")
    private Date gmtUserExp;

    @Column(name = "image_url")
    @ApiModelProperty("图片地址")
    private String imageUrl;

    @Column(name = "user_status")
    @ApiModelProperty("用户状态（1.正常；2.锁定；3.注销）")
    private String userStatus;

    @Column(name = "gmt_lock")
    @ApiModelProperty("用户锁定时间")
    private Date gmtLock;

    @Column(name = "gmt_unlock")
    @ApiModelProperty("用户解锁时间")
    private Date gmtUnlock;

    @Column(name = "reason_lock")
    @ApiModelProperty("用户锁定原因")
    private String reasonLock;

    @Column(name = "gmt_login_last")
    @ApiModelProperty("最后一次登录时间")
    private Date gmtLoginLast;

    @Column(name = "login_ip_last")
    @ApiModelProperty("最后一次登录IP")
    private String loginIpLast;

    @Column(name = "gmt_modified")
    @ApiModelProperty("修改时间")
    private Date gmtModified;

    @Column(name = "gmt_create")
    @ApiModelProperty("创建时间")
    private Date gmtCreate;

}
