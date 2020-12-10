package com.zhanghf.vo.excel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhanghf
 * @version 1.0
 * @date 15:49 2020/11/27
 */
@Data
@NoArgsConstructor
public class ExportExcelVO implements Serializable {

    @ApiModelProperty("excel行头内容")
    private String nameValue;

    @ApiModelProperty("excel每行值的key")
    private String keyValue;

    @ApiModelProperty("行宽")
    private Integer columnWidth;

    public ExportExcelVO(String nameValue, String keyValue, Integer columnWidth) {
        this.nameValue = nameValue;
        this.keyValue = keyValue;
        this.columnWidth = columnWidth;
    }
}
