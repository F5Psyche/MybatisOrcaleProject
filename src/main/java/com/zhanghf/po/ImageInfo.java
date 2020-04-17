package com.zhanghf.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:14 2020/4/17
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageInfo implements Serializable {

    private String imageUuid;

    private String imageBaseCode;
}
