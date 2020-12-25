package com.zhanghf.controller.login;

import com.alibaba.fastjson.JSON;
import com.zhanghf.modues.login.SystemLoginService;
import com.zhanghf.po.login.UserInfo;
import com.zhanghf.util.CommonUtils;
import com.zhanghf.util.EncryptUtils;
import com.zhanghf.vo.ResultVO;
import com.zhanghf.vo.login.LoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:58 2020/12/7
 */
@Slf4j
@RestController
@RequestMapping("user")
public class SystemLoginController {

    @Resource
    SystemLoginService systemLoginService;

    @RequestMapping("login")
    public ResultVO<String> userLogin(@RequestBody LoginInfoVO info) {
        String uuid = UUID.randomUUID().toString();
        ResultVO<String> resultVo = new ResultVO<>(uuid);
        try {
            long currentTime = System.currentTimeMillis();
            String ssoToken = EncryptUtils.md5(JSON.toJSONString(info));
            systemLoginService.isLogin("123456");
        } catch (Exception e) {
            resultVo = CommonUtils.getExceptionResult(uuid, e);
        }
        return resultVo;
    }

    public ResultVO<Map<String, Object>> userRegister(@RequestBody UserInfo info) {
        String uuid = UUID.randomUUID().toString();
        ResultVO<Map<String, Object>> resultVo = new ResultVO<>(uuid);


        return resultVo;
    }
}
