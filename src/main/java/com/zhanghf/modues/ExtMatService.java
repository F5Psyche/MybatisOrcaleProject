package com.zhanghf.modues;

import com.zhanghf.annotation.CustomPermissionsService;
import com.zhanghf.enums.BusinessCodeEnum;
import com.zhanghf.enums.RoleEnum;
import com.zhanghf.mapper.OrganInfoMapper;
import com.zhanghf.po.ImageInfo;
import com.zhanghf.po.OrganInfo;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author zhanghf
 * @version 1.0
 */
@Slf4j
@Service
public class ExtMatService {

    @Resource
    OrganInfoMapper organInfoMapper;

    /**
     * 查询机构信息表里的所有数据
     *
     * @param uuid 唯一识别码
     * @return 机构信息的集合
     */
    @CustomPermissionsService(role = RoleEnum.ADMIN)
    public List<OrganInfo> organInfoAll(String uuid) {
        List<OrganInfo> list = organInfoMapper.selectAll();
        if (CollectionUtils.isEmpty(list)) {
            log.info("uuid={}, list={}", uuid, list);
            return Collections.emptyList();
        }
        return list;
    }

    @CustomPermissionsService(role = RoleEnum.ADMIN)
    public ResultVo<List<OrganInfo>> organInfoList(String uuid, ImageInfo imageInfo) {
        ResultVo<List<OrganInfo>> resultVo = new ResultVo<>(uuid);
        List<OrganInfo> list = organInfoMapper.organInfoList(new OrganInfo());
        if (CollectionUtils.isEmpty(list)) {
            log.info("uuid={}, list={}", uuid, list);
        }
        resultVo.setResult(list);
        resultVo.setCode(BusinessCodeEnum.SUCCESS.getCode());
        resultVo.setResultDes(BusinessCodeEnum.SUCCESS.getMsg());
        resultVo.setSuccess(true);
        return resultVo;
    }


}
