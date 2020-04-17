package com.zhanghf.mapper;

import com.zhanghf.po.OrganInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zhanghf
 * @version 1.0
 */
public interface OrganInfoMapper extends Mapper<OrganInfo> {

    /**
     * 查询机构信息
     *
     * @param organInfo 查询条件
     * @return 查询结果
     */
    List<OrganInfo> organInfoList(OrganInfo organInfo);
}
