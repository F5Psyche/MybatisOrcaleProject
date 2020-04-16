package com.zhanghf.enums;

import lombok.Getter;

/**
 * @author zhanghf
 * @version 1.0
 * @date 17:38 2020/4/15
 */
@Getter
public enum CustomPermissionsEnum {
    /**
     * 普通用户
     */
    USER(0),
    /**
     * 普通会员
     */
    ADMIN(1),
    /**
     * 超级会员
     */
    SYSTEM_ADMIN(2),
    ;

    private Integer grade;

    CustomPermissionsEnum(Integer grade) {
        this.grade = grade;
    }
}
