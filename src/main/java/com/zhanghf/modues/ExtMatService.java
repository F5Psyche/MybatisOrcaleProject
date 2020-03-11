package com.zhanghf.modues;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhanghf.dto.CommonDTO;
import com.zhanghf.dto.MatDicDTO;
import com.zhanghf.mapper.*;
import com.zhanghf.po.*;
import com.zhanghf.util.HttpConnectionUtils;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class ExtMatService {

    @Resource
    ExtChargeTabMapper extChargeTabMapper;

    @Resource
    ExtMatBasicInfoMapper extMatBasicInfoMapper;

    @Resource
    ExtMaterialTabMapper extMaterialTabMapper;

    @Resource
    ExtMatTabMapper extMatTabMapper;

    @Resource
    OrganInfoTabMapper organInfoTabMapper;

    public List<ExtMatTab> extMatAll(String uuid) {
        List<ExtMatTab> list = extMatTabMapper.selectAll();
        if (CollectionUtils.isEmpty(list)) {
            log.info("uuid={}, list={}", uuid, list);
            return Collections.emptyList();
        }
        return list;
    }

    /**
     * 查询机构信息表里的所有数据
     *
     * @param uuid 唯一识别码
     * @return 机构信息的集合
     */
    public List<OrganInfoTab> organInfoAll(String uuid) {
        List<OrganInfoTab> list = organInfoTabMapper.selectAll();
        if (CollectionUtils.isEmpty(list)) {
            log.info("uuid={}, list={}", uuid, list);
            return Collections.emptyList();
        }
        return list;
    }

    public List<OrganInfoTab> organInfoList(String uuid) {
        Map<String, Object> map = new HashMap<>();
        List<OrganInfoTab> list = organInfoTabMapper.organInfoList(map);
        if (CollectionUtils.isEmpty(list)) {
            log.info("uuid={}, list={}", uuid, list);
            return Collections.emptyList();
        }
        return list;
    }


    public ResultVo matterSimpleQuery(String uuid, OrganInfoTab organInfoTab) {
        ResultVo resultVo = new ResultVo();
        JSONObject params = new JSONObject();
        params.put("jurisCode", organInfoTab.getAreaCode());
        params.put("ouGuid", organInfoTab.getOrganId());
        JSONObject result = getResult(uuid, CommonDTO.MATTER_SIMPLE_URL, params);
        int items = result.getInteger("items");
        if (items > 100) {
            int size = items / 100 + 1;
            for (int i = 0; i < size; i++) {
                params.put("current", i + 1);
                params.put("size", "100");
                extMatInfoSave(uuid, getResult(uuid, CommonDTO.MATTER_SIMPLE_URL, params));
            }
        } else {
            extMatInfoSave(uuid, result);
        }
        return resultVo;
    }

    public ResultVo matterDetailQuery(String uuid, String localInnerCode) {
        ResultVo resultVo = new ResultVo();
        JSONObject params = new JSONObject();
        JSONObject result = new JSONObject();
        JSONObject json = new JSONObject();
        try {
            params.put("localInnerCode", localInnerCode);
            result = getResult(uuid, CommonDTO.MATTER_DETAIL_URL, params);
            json = result.getJSONObject("implementDetailDTO");
            extChargeSave(uuid, localInnerCode, json);
            extMaterialSave(uuid, localInnerCode, json);
            extBasicInfoSave(uuid, json);
        } catch (Exception e) {
            log.error("uuid={}, json={}, result={}, errMsg={}", uuid, json, result, e.toString());
        }
        return resultVo;
    }

    private ResultVo extMatInfoSave(String uuid, JSONObject content) {
        ResultVo resultVo = new ResultVo();
        String data = content.getString("basicInfoDTOs");
        List<ExtMatBasicInfo> extMatBasicInfos = JSON.parseArray(data, ExtMatBasicInfo.class);
        for (ExtMatBasicInfo extMatBasicInfo : extMatBasicInfos) {
            String serviceCodeId = extMatBasicInfo.getImpletype() + "-" + extMatBasicInfo.getMainitemcode() + "-" + extMatBasicInfo.getSubitemcode();
            String impleName = MatDicDTO.matterMap.get(extMatBasicInfo.getImpletype());
            String serviceCode = "";
            if (impleName != null) {
                serviceCode = impleName + "-" + extMatBasicInfo.getMainitemcode() + "-" + extMatBasicInfo.getSubitemcode();
            }
            if ("1".equals(extMatBasicInfo.getDetailitem())) {
                serviceCodeId = serviceCodeId + "-" + extMatBasicInfo.getDetailitemcode();
            }
            ExtMatTab extMatTab = new ExtMatTab();
            extMatTab.setServicecodeid(serviceCodeId);
            extMatTab.setServicecode(serviceCode);
            extMatTab.setOuguid(extMatBasicInfo.getOuguid());
            extMatTab.setLocalrowguid(extMatBasicInfo.getLocalrowguid());
            extMatTab.setLocalinnercode(extMatBasicInfo.getLocalinnercode());
            extMatTab.setJuriscode(extMatBasicInfo.getJuriscode());
            extMatTab.setEntityname(extMatBasicInfo.getEntityname());
            extMatTab.setMatname(extMatBasicInfo.getMatname());
            extMatTabMapper.insertSelective(extMatTab);
        }
        return resultVo;
    }

    private ResultVo extChargeSave(String uuid, String localInnerCode, JSONObject content) {
        ResultVo resultVo = new ResultVo();
        String chargeDTOs = null;
        List<ExtChargeTab> extChargeTabs = new ArrayList<>();
        try {
            chargeDTOs = content.getString("chargeDTOs");
            extChargeTabs = JSON.parseArray(chargeDTOs, ExtChargeTab.class);
            log.info("uuid={}, chargeDTOs={}, extChargeTabs={}", uuid, chargeDTOs, extChargeTabs);
            for (ExtChargeTab extChargeTab : extChargeTabs) {
                extChargeTab.setLocalinnercode(localInnerCode);
                extChargeTabMapper.insertSelective(extChargeTab);
            }
        } catch (Exception e) {
            log.error("uuid={}, chargeDTOs={}, extChargeTabs={}, errMsg={}", uuid, chargeDTOs, extChargeTabs, e.toString());
        }
        return resultVo;
    }

    private ResultVo extMaterialSave(String uuid, String localInnerCode, JSONObject content) {
        ResultVo resultVo = new ResultVo();
        String materialDTOs = null;
        List<ExtMaterialTab> extMaterialTabs = new ArrayList<>();
        try {
            materialDTOs = content.getString("materialDTOs");
            extMaterialTabs = JSON.parseArray(materialDTOs, ExtMaterialTab.class);
            for (ExtMaterialTab extMaterialTab : extMaterialTabs) {
                extMaterialTab.setLocalinnercode(localInnerCode);
                extMaterialTabMapper.insertSelective(extMaterialTab);
            }
        } catch (Exception e) {
            log.error("uuid={}, materialDTOs={}, extMaterialTabs={}, errMsg={}", uuid, materialDTOs, extMaterialTabs, e.toString());
        }
        return resultVo;
    }

    private ResultVo extBasicInfoSave(String uuid, JSONObject content) {
        ResultVo resultVo = new ResultVo();
        String basicInfoDTO = null;
        ExtMatBasicInfo extMatBasicInfo = new ExtMatBasicInfo();
        try {
            basicInfoDTO = content.getString("basicInfoDTO");
            extMatBasicInfo = content.getObject("basicInfoDTO", ExtMatBasicInfo.class);
            log.info("uuid={}, basicInfoDTO={}, extMatBasicInfo={}", uuid, basicInfoDTO, extMatBasicInfo);
            extMatBasicInfoMapper.insertSelective(extMatBasicInfo);
        } catch (Exception e) {
            log.error("uuid={}, basicInfoDTO={}, extMatBasicInfo={}, errMsg={}", uuid, basicInfoDTO, extMatBasicInfo, e.toString());
        }
        return resultVo;
    }

    private JSONObject getResult(String uuid, String url, JSONObject params) {
        Object obj = HttpConnectionUtils.httpConnectionPost(uuid, url, params);
        String data = JSON.toJSONString(obj);
        JSONObject json = JSON.parseObject(data);
        JSONArray array = json.getJSONArray(CommonDTO.COMMON_KEY);
        JSONObject result = array.getJSONObject(CommonDTO.COMMON_INDEX);
        return result;
    }


}
