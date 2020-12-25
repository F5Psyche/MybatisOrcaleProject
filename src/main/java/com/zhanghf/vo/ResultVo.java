package com.zhanghf.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:11 2020/3/10
 */
@Data
@NoArgsConstructor
public class ResultVO<T> implements Serializable {

    @ApiModelProperty("返回结果")
    private T result;

    @ApiModelProperty("是否成功")
    private boolean isSuccess = false;

    @ApiModelProperty("错误编码")
    private String code;

    @ApiModelProperty("错误描述")
    private String resultDes;

    @ApiModelProperty("请求ID")
    private String requestId;

    public ResultVO(String requestId) {
        this.requestId = requestId;
    }

    public ResultVO(String code, String resultDes, String requestId) {
        this.code = code;
        this.resultDes = resultDes;
        this.requestId = requestId;
    }
}