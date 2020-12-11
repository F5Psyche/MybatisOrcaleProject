package com.zhanghf.controller.image;

import com.zhanghf.dto.CommonDTO;
import com.zhanghf.modues.ImageInfoService;
import com.zhanghf.po.OrganInfo;
import com.zhanghf.util.CommonUtils;
import com.zhanghf.vo.ResultVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:03 2020/3/23
 */
@Slf4j
@RestController
@RequestMapping("")
@Api(tags = "图片")
public class ImageController {

    @Resource
    ImageInfoService imageInfoService;

    @PostMapping("/image/generate")
    public ResultVo<List<String>> imageGenerate(@RequestParam(value = "imageUuid", required = false) String imageUuid) {
        String uuid = UUID.randomUUID().toString();
        return imageInfoService.imageGenerate(uuid, imageUuid);
    }


    @PostMapping("/body/test")
    public boolean bodyTest(@RequestBody OrganInfo organInfo) {
        log.info("organInfo={}", organInfo);
        imageInfoService.bodyTest(organInfo);
        return false;
    }


    @PostMapping(value = "/getFileInfo")
    public Map<String, Object> getFileHttpUrl(@RequestPart("file") MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        Map<String, Object> map = new HashMap<>(CommonDTO.INITIAL_CAPACITY);
        try (
                InputStream inputStream = file.getInputStream()
        ) {
            // in.available()返回文件的字节长度
            byte[] bytes = new byte[inputStream.available()];
            // 将文件中的内容读入到数组中
            int nums = inputStream.read(bytes);
            String base64Code = Base64.encodeBase64String(bytes);
            String fileContentType = file.getContentType();
            log.info("uuid={}, nums={}, fileContentType={}", uuid, nums, fileContentType);
        } catch (IOException e) {
            log.error("uuid={}, errMsg={}", uuid, CommonUtils.getStackTraceString(e));
        }

        return map;
    }
}
