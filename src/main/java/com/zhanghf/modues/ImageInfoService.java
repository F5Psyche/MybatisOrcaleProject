package com.zhanghf.modues;

import com.zhanghf.mapper.ImageInfoMapper;
import com.zhanghf.po.ImageInfo;
import com.zhanghf.po.OrganInfo;
import com.zhanghf.util.ImageUtils;
import com.zhanghf.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghf
 * @version 1.0
 * @date 17:14 2020/4/17
 */
@Slf4j
@Service
public class ImageInfoService {

    @Resource
    ImageInfoMapper imageInfoMapper;

    public ResultVO<List<String>> imageGenerate(String uuid, String imageUuid) {
        ResultVO<List<String>> resultVo = new ResultVO<>(uuid);
        List<ImageInfo> imageInfos = imageInfoMapper.select(new ImageInfo(imageUuid));
        List<String> list = new ArrayList<>();
        for (ImageInfo imageInfo : imageInfos) {
            ImageUtils.imageDownloadWithCode(imageUuid, imageInfo.getImageBaseCode(), new File("C:\\photo\\" + imageUuid + ".pdf"));
            list.add(imageInfo.getImageUuid());
        }
        resultVo.setResult(list);
        String insMatId = "2";
        log.info("insMatId={}", insMatId);
        return resultVo;
    }


    public boolean bodyTest(OrganInfo organInfo) {
        if (StringUtils.isEmpty(organInfo.getAreaCode())) {
            throw new NullPointerException("统筹区为空");
        }
        log.info("organInfo={}", organInfo);
        return false;
    }
}
