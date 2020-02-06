package com.zhanghf.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhanghf.modues.PowerMatterService;
import com.zhanghf.po.OrganInfoTab;
import com.zhanghf.po.PowerMatterInfoTab;
import com.zhanghf.util.HttpServletRequestUtils;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("")
public class PowerMatterController {

    @Resource
    PowerMatterService powerMatterService;

    @RequestMapping("/power/matter")
    public ResultVo powerMatterInfoList(HttpServletRequest request) {
        ResultVo resultVo = new ResultVo();
        String uuid = UUID.randomUUID().toString();
        JSONObject paramter = HttpServletRequestUtils.getParameter(uuid, request);
        List<PowerMatterInfoTab> list = powerMatterService.powerMatterInfoList(uuid);
        log.info("uuid={}, paramter={}, list={}", uuid, paramter, list);
        resultVo.setResult(list);
        resultVo.setSuccess(true);
        return resultVo;
    }

    @RequestMapping("/power/matter/insert")
    public ResultVo insertPowerMatterInfo(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        JSONObject params = HttpServletRequestUtils.getParameter(uuid, request);
        ResultVo resultVo = powerMatterService.insertPowerMatterInfo(uuid, params);
        log.info("uuid={}, params={}, resultVo={}", uuid, params, resultVo);
        return resultVo;
    }

    @RequestMapping("/organ")
    public ResultVo organInfoList(@RequestParam(value = "organId", required = false) String organId, @RequestParam(value = "organArea", required = false) String organArea) {
        ResultVo resultVo = new ResultVo();
        String uuid = UUID.randomUUID().toString();
        List<OrganInfoTab> list = powerMatterService.organInfoList(uuid, organId, organArea);
        log.info("uuid={}, organId={}, organArea={}, list={}", uuid, organId, organArea, list);
        resultVo.setResult(list);
        resultVo.setSuccess(true);
        return resultVo;
    }
}
