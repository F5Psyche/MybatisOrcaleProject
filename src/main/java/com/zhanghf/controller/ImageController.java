package com.zhanghf.controller;

import com.zhanghf.vo.InnerMatterMaterialInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:03 2020/3/23
 */
@Slf4j
@RestController
@RequestMapping("")
public class ImageController {

    @PostMapping("/image/generate")
    public boolean imageGenerate(@RequestParam(value = "imageUuid", required = false) String imageUuid) {


        return false;
    }

    @PostMapping("/annotation/test")
    public boolean annotationTest(@RequestBody InnerMatterMaterialInfoVO vo) {

        log.info("vo={}", vo);
        return false;
    }
}
