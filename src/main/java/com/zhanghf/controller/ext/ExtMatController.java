package com.zhanghf.controller.ext;

import com.alibaba.fastjson.JSONObject;
import com.zhanghf.annotation.CustomPermissionsController;
import com.zhanghf.annotation.RoleNum;
import com.zhanghf.enums.BusinessCodeEnum;
import com.zhanghf.enums.RoleEnum;
import com.zhanghf.modues.ExtMatService;
import com.zhanghf.modues.login.SystemLoginService;
import com.zhanghf.po.ImageInfo;
import com.zhanghf.po.OrganInfo;
import com.zhanghf.util.HttpServletRequestUtils;
import com.zhanghf.vo.ResultVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @author zhanghf
 * @version 1.0
 * @date 12:15 2020/2/17
 */
@Slf4j
@RestController
@RequestMapping("ext")
@Api(tags = "外部事项")
public class ExtMatController {

    @Resource
    ExtMatService extMatService;

    @Resource
    SystemLoginService systemLoginService;

    @RequestMapping("/organ")
    @RoleNum(role = RoleEnum.ADMIN)
    public ResultVo organInfoList(HttpServletRequest request) {
        ResultVo resultVo = new ResultVo();
        String uuid = UUID.randomUUID().toString();
        JSONObject params = HttpServletRequestUtils.getParameter(uuid, request);
        log.info("uuid={}, params={}, header={}", uuid, params, request.getHeader("ssToken"));
        List<OrganInfo> list = extMatService.organInfoAll(uuid);
        systemLoginService.isLogin(request.getHeader("ssToken"));
        resultVo.setResult(list);
        resultVo.setSuccess(true);
        resultVo.setResultDes("");
        return resultVo;
    }

    @RequestMapping("/ext/matter")
    @CustomPermissionsController(role = RoleEnum.ADMIN)
    public ResultVo extMatInfoSave(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        ResultVo resultVo = new ResultVo(uuid);
        try {
            ResultVo<List<OrganInfo>> organInfoResult = extMatService.organInfoList(uuid, new ImageInfo("imageSan"));
            if (organInfoResult.isSuccess()) {
                List<OrganInfo> list = organInfoResult.getResult();
                for (OrganInfo organInfo : list) {
                    log.info("organInfo={}", organInfo);
                }
            } else {
                resultVo = organInfoResult;
            }
        } catch (Exception e) {
            resultVo.setCode(BusinessCodeEnum.UNKNOWN_ERROR.getCode());
            resultVo.setResultDes(BusinessCodeEnum.UNKNOWN_ERROR.getMsg());
            log.error("uuid={}, resultVo={}, request={}, errMsg={}", uuid, resultVo, request, e.toString());
        }
        return resultVo;
    }

//    @RequestMapping("/matterDetail/query")
//    public ResultVo matterDetailQuery(@RequestParam(value = "localInnerCode", required = false) String localInnerCode) {
//        String uuid = UUID.randomUUID().toString();
//        ResultVo resultVo = new ResultVo();
//        try {
//            if (StringUtils.isEmpty(localInnerCode)) {
//                List<ExtMatTab> list = extMatService.extMatAll(uuid);
//                for (ExtMatTab extMatTab : list) {
//                    resultVo = extMatService.matterDetailQuery(uuid, extMatTab.getLocalinnercode());
//                }
//            } else {
//                resultVo = extMatService.matterDetailQuery(uuid, localInnerCode);
//            }
//
//        } catch (Exception e) {
//            log.error("uuid={}, resultVo={}, localInnerCode={}", uuid, resultVo, localInnerCode);
//        }
//        return resultVo;
//    }
}
