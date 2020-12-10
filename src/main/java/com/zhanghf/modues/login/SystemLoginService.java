package com.zhanghf.modues.login;

import com.zhanghf.mapper.login.LoginNotesInfoMapper;
import com.zhanghf.po.login.LoginNotesInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zhanghf
 * @version 1.0
 * @date 15:23 2020/12/7
 */
@Slf4j
@Service
public class SystemLoginService {

    @Resource
    LoginNotesInfoMapper loginNotesInfoMapper;

    public boolean loginNotesInfoSave(LoginNotesInfo info) {
        info.setIsInvalid("0");
        int i = loginNotesInfoMapper.insertSelective(info);
        return i > 0;
    }

    public boolean isLogin(String ssoToken) {
        Example example = new Example(LoginNotesInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ssoToken", ssoToken);
        criteria.andGreaterThanOrEqualTo("gmtInvalid", new Date());
        criteria.andEqualTo("isInvalid", "0");
        List<LoginNotesInfo> loginNotesInfos = loginNotesInfoMapper.selectByExample(example);
        log.info("loginNotesInfos={}", loginNotesInfos);
        return CollectionUtils.isEmpty(loginNotesInfos);
    }
}
