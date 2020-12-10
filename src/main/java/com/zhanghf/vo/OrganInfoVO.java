package com.zhanghf.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhanghf
 * @version 1.0
 * @date 15:24 2020/12/4
 */
@Data
public class OrganInfoVO implements Serializable {

    @ApiModelProperty("统筹区")
    private String aab301;

    @ApiModelProperty("上级统筹区")
    private String aab304;

    @ApiModelProperty("统筹区名称")
    private String aae044;

    @ApiModelProperty("机构编码")
    private String organId;

    @ApiModelProperty("机构名称")
    private String organName;
}
