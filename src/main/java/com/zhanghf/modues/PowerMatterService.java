package com.zhanghf.modues;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhanghf.mapper.OrganInfoTabMapper;
import com.zhanghf.mapper.PowerMatterInfoTabMapper;
import com.zhanghf.po.OrganInfoTab;
import com.zhanghf.po.PowerMatterInfoTab;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class PowerMatterService {

    @Resource
    PowerMatterInfoTabMapper powerMatterInfoTabMapper;

    @Resource
    OrganInfoTabMapper organInfoTabMapper;

    public List<PowerMatterInfoTab> powerMatterInfoList(String uuid) {
        List<PowerMatterInfoTab> list = powerMatterInfoTabMapper.selectAll();
        if (CollectionUtils.isEmpty(list)) {
            log.info("uuid={}, list={}", uuid, list);
            return Collections.emptyList();
        }
        return list;
    }

    public ResultVo insertPowerMatterInfo(String uuid, JSONObject params) {
        PowerMatterInfoTab powerMatterInfoTab = JSON.toJavaObject(params, PowerMatterInfoTab.class);
        int i = powerMatterInfoTabMapper.insertSelective(powerMatterInfoTab);
        if (i == 0) {
            log.info("uuid={}, i={}", uuid, i);
            return null;
        }
        return null;
    }

    public List<OrganInfoTab> organInfoList(String uuid, String organId, String areaCode) {
        OrganInfoTab organInfo = new OrganInfoTab();
        organInfo.setOrganId(organId);
        organInfo.setOrganArea(areaCode);
        List<OrganInfoTab> list = organInfoTabMapper.select(organInfo);
        if (CollectionUtils.isEmpty(list)) {
            log.info("uuid={}, list={}", uuid, list);
            return Collections.emptyList();
        }
        return list;
    }

}
