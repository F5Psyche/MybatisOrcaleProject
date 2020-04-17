package com.zhanghf.modues;

import com.zhanghf.mapper.ImageInfoMapper;
import com.zhanghf.po.ImageInfo;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public ResultVo imageGenerate(String uuid, String imageUuid) {
        ResultVo resultVo = new ResultVo(uuid);
        List<ImageInfo> imageInfos = imageInfoMapper.select(new ImageInfo(imageUuid));
        for (ImageInfo imageInfo : imageInfos) {

        }
        return resultVo;
    }
}
