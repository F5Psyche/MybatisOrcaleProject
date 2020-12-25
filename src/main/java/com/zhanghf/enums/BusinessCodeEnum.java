package com.zhanghf.enums;

import com.zhanghf.vo.ResultVO;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:35 2020/3/11
 */
@Getter
public enum BusinessCodeEnum {
    SUCCESS("0000", "成功!", ""),
    TRADE_CODE_NOT_FIND("2201", "交易码不存在", ""),
    PARAMS_ERROR("2202", "传入参数有误", ""),
    COL_MUST_FILL("2203", "字段不能为空", ""),
    CONTENT_EMPTY("2204", "内容不能为空", ""),
    /**
     * 数据库数据有误,联系相关人员
     */
    MYSQL_DATA_ERROR("2205", "联系相关人员", ""),
    DATA_SAVE_ERROR("2206", "数据存储失败", ""),
    DATA_DELETE_ERROR("2207", "数据删除失败", ""),
    DATA_UPDATE_ERROR("2208", "数据更新失败", ""),
    DATA_QUERY_ERROR("2209", "数据查询失败", ""),
    LOGIN_INFO_EMPTY("2210", "登陆用户信息获取失败", ""),

    TIME_LIMIT_ERROR("2221", "预警时限不能大于办理时限", ""),
    SERVICE_CODE_ERROR("2222", "事项基本码格式错误", ""),
    MATTER_PROCESS_NOT_FIND("2223", "事项流程不存在", ""),
    MATTER_PROCESS_TOO_MANY("2224", "涉及到的事项流程大于1", ""),
    MATTER_IS_EXIST("2225", "事项已经存在", ""),
    MOULD_NAME_IS_EXIST("2226", "模板名称已经存在", ""),
    REQUEST_URL_ERROR("2227", "请求地址错误", ""),


    DATA_QUERY_NULL("2290", "没有查询到相关数据", ""),

    UNKNOWN_ERROR("2299", "未知异常", ""),
    ;

    private String code;
    private String msg;
    private String data;

    BusinessCodeEnum(String code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultVO<T> getMsgCode(String uuid, String data) {
        ResultVO<T> resultVo = new ResultVO<>(UNKNOWN_ERROR.getMsg(), UNKNOWN_ERROR.getCode(), uuid);
        if (data != null && data.length() > 0) {
            for (BusinessCodeEnum type : BusinessCodeEnum.values()) {
                if (StringUtils.isNotBlank(type.getData()) && data.contains(type.getData())) {
                    resultVo.setResultDes(type.getMsg());
                    resultVo.setCode(type.getCode());
                    if (SUCCESS.getCode().equals(type.getCode()) || DATA_QUERY_NULL.getCode().equals(type.getCode())) {
                        resultVo.setSuccess(true);
                    }
                    return resultVo;
                }
            }
        }
        return resultVo;
    }
}
