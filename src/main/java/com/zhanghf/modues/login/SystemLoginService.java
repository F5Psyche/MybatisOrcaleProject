package com.zhanghf.modues.login;

import com.zhanghf.annotation.CustomPermissionsService;
import com.zhanghf.enums.RoleEnum;
import com.zhanghf.mapper.login.UserInfoMapper;
import com.zhanghf.po.login.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author zhanghf
 * @version 1.0
 * @date 15:23 2020/12/7
 */
@Slf4j
@Service
public class SystemLoginService {

    @Resource
    UserInfoMapper userInfoMapper;

    public boolean loginNotesInfoSave(UserInfo info) {
        int i = userInfoMapper.insertSelective(info);
        return i > 0;
    }

    public boolean isLogin(String ssoToken) {
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        log.info("ssoToken={}", ssoToken);
        return false;
    }
}
