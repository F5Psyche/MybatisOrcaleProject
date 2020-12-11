package com.zhanghf.controller.login;

import com.alibaba.fastjson.JSON;
import com.zhanghf.enums.BusinessCodeEnum;
import com.zhanghf.modues.login.SystemLoginService;
import com.zhanghf.po.login.LoginNotesInfo;
import com.zhanghf.util.Base64Utils;
import com.zhanghf.util.CommonUtils;
import com.zhanghf.util.EncryptUtils;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:58 2020/12/7
 */
@Slf4j
@RestController
@RequestMapping("login")
public class SystemLoginController {

    @Resource
    SystemLoginService systemLoginService;

    @RequestMapping("/login")
    public ResultVo<String> systemLogin(@RequestBody LoginNotesInfo info) {
        String uuid = UUID.randomUUID().toString();
        ResultVo<String> resultVo = new ResultVo<>(uuid);
        try {
            long currentTime = System.currentTimeMillis();
            info.setGmtCreate(new Date(currentTime));
            info.setGmtInvalid(new Date(currentTime + 15 * 60 * 1000));
            String base64Code = Base64Utils.base64Code(JSON.toJSONString(info));
            info.setBaseCode(base64Code);
            String ssoToken = EncryptUtils.md5(JSON.toJSONString(info));
            info.setSsoToken(ssoToken);
            if (systemLoginService.loginNotesInfoSave(info)) {
                resultVo.setResult(ssoToken);
                resultVo.setResultDes(BusinessCodeEnum.SUCCESS.getMsg());
                resultVo.setCode(BusinessCodeEnum.SUCCESS.getCode());
                resultVo.setSuccess(true);
            }
        } catch (Exception e) {
            resultVo = CommonUtils.getExceptionResult(uuid, e);
        }
        return resultVo;
    }
}
