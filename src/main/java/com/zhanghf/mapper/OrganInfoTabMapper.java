package com.zhanghf.mapper;

import com.zhanghf.po.OrganInfoTab;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface OrganInfoTabMapper extends Mapper<OrganInfoTab> {

    List<OrganInfoTab> organInfoList(Map<String, Object> map);
}
