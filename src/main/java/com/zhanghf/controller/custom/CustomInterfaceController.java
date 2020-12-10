package com.zhanghf.controller.custom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhanghf.vo.ResultVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 使用自定义注解的Controller
 *
 * @author zhanghf
 * @version 1.0
 * @date 16:16 2020/4/16
 */
@Slf4j
@RestController
@RequestMapping("custom")
@Api(tags = "切面")
public class CustomInterfaceController {

    @PostMapping("/get/resultVo")
    public ResultVo getResultVo() {
        String uuid = UUID.randomUUID().toString();
        ResultVo resultVo = new ResultVo(uuid);
        String data = "{\"licenses\":[{\"liceseType\":\"0\",\"licenseUid\":\"1adfcd91-76fb-4e11-9666-4fe43187576e\",\"licenseUrl\":\"http://ybj.zjzwfw.gov.cn:10540/openapiApp/download?key=bizamt/rdm/1590032874358X1S.pdf\",\"licenseNo\":\"1001\",\"materialType\":\"pdf\",\"licenseName\":\"参保凭证\",\"licenseUrlNum\":\"1\"}],\"attachments\":[{\"attUrl\":\"http://ybj.zjzwfw.gov.cn:10540/openapiApp/download?key=bizamt/rdm/1590032874358X1S.pdf\",\"attType\":\"pdf\",\"attName\":\"参保凭证\"}],\"memo\":\"办理意见\",\"finishCode\":\"FINISHED\",\"finishForm\":{}}";
        JSONObject result = JSON.parseObject(data);
        resultVo.setResult(result);
        resultVo.setCode(JSON.toJSONString(result));
        return resultVo;
    }

}
