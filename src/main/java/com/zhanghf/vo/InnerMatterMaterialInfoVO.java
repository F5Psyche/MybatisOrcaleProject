package com.zhanghf.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zhanghf.annotation.CheckNull;
import com.zhanghf.po.AddressInfo;
import com.zhanghf.po.InnerMaterialInfo;
import com.zhanghf.po.InnerMatterInfo;
import com.zhanghf.po.InnerMatterUserInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhanghf
 * @version 1.0
 * @date 18:27 2020/2/17
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InnerMatterMaterialInfoVO implements Serializable {

    private InnerMatterInfo matterInfo;

    private List<InnerMaterialInfo> materialInfos;

    private List<InnerMatterUserInfo> matterUserInfos;

    @CheckNull(message = "受理地址不能为空")
    private List<AddressInfo> addressInfos;

}
