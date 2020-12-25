package com.zhanghf.util;


import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import com.zhanghf.dto.CommonDTO;
import com.zhanghf.exception.InputParamNullException;
import com.zhanghf.exception.JavaWebTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * jwt
 *
 * @author zhanghf
 * @version 1.0
 * @date 15:12 2020/12/25
 */
@Slf4j
public class JavaWebTokenUtils {


    /**
     * 密钥
     */
    private static final String SECRET = "rds2#!G9Fds%^&Gg4>aV0@s]E56*Gh^3<Ud8(Rf3}Mxs1$mq5~d7JClR";

    /**
     * key值
     */
    private static final String PAYLOAD = "payload";

    /**
     * 默认有效时长（单位毫秒）
     */
    private static final long DEFAULT_VALID_TIME = 60000;


    /**
     * 生成token
     *
     * @param uuid        唯一识别码
     * @param object      对象
     * @param loginTime   登录时间
     * @param maxVailTime 有效时间
     * @return token
     */
    public static String tokenGenerate(String uuid, Object object, long loginTime, Long maxVailTime) {
        try {
            if (object != null) {
                long expiresTime = loginTime + (StringUtils.isEmpty(maxVailTime) ? DEFAULT_VALID_TIME : maxVailTime);
                Map<String, Object> headerClaims = Maps.newHashMap();
                headerClaims.put("alg", "HS256");
                headerClaims.put("type", "JWT");
                String data = JSON.toJSONString(object);
                JWTCreator.Builder builder = JWT.create();
                builder.withHeader(headerClaims)
                        .withClaim(PAYLOAD, data)
                        .withExpiresAt(new Date(expiresTime));
                return builder.sign(Algorithm.HMAC256(SECRET));
            } else {
                throw new NullPointerException("对象不能为空");
            }
        } catch (Exception e) {
            log.error(CommonDTO.COMMON_LOGGER_ERROR_INFO_PARAM, uuid, CommonUtils.getStackTraceString(e));
            throw new JavaWebTokenException("token生成异常");
        }
    }

    /**
     * token解析
     *
     * @param uuid  唯一识别码
     * @param token token
     * @param clazz 对象属性
     * @param <T>   泛型
     * @return 对象
     */
    public static <T> T tokenParse(String uuid, String token, Class<T> clazz) {
        try {
            if (StringUtils.isEmpty(token)) {
                throw new InputParamNullException("token");
            }
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            log.info("exp={}", CommonUtils.getTime("yyyy-MM-dd HH:mm:ss", claims.get("exp").asLong() * 1000));
            if (claims.containsKey(PAYLOAD)) {
                String data = claims.get(PAYLOAD).asString();
                return JSON.parseObject(data, clazz);
            } else {
                throw new JavaWebTokenException("token解析异常");
            }
        } catch (Exception e) {
            log.error(CommonDTO.COMMON_LOGGER_ERROR_INFO_PARAM, uuid, CommonUtils.getStackTraceString(e));
            throw new JavaWebTokenException("token解析异常");
        }
    }

}
