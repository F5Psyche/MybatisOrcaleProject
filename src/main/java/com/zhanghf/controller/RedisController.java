package com.zhanghf.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.zhanghf.annotation.RoleNum;
import com.zhanghf.enums.RoleEnum;
import com.zhanghf.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhanghf
 * @version 1.0
 * @date 9:33 2020/12/17
 */
@Slf4j
@RestController
@RequestMapping("redis")
public class RedisController {

    @Resource
    RedisUtils redisUtils;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("set")
    public Object redisSet(@RequestParam(value = "redisKey") String key) {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        log.info("key={}, value={}", key, list);
        return redisUtils.set(key, list);
    }

    @RequestMapping("get")
    public Object redisGet(@RequestParam(value = "redisKey") String key) {
        return redisUtils.get(key);
    }

    @RequestMapping("delete")
    public Object redisDelete(@RequestParam(value = "redisKey") String key) {
        redisUtils.del(key);
        return true;
    }

    @RequestMapping("expire")
    public boolean redisExpire(@RequestParam(value = "redisKey") String key) {
        return redisUtils.expire(key, 3L);
    }

    @RequestMapping("allMethod")
    public Object redisAllMethod(@RequestParam(value = "redisKey") String key) {
        try {
            Map<String, Object> map = ImmutableMap.of("loginName", "ZhangSan", "loginPassword", "123456");
            redisTemplate.opsForHash().putAll(key, map);
            redisTemplate.opsForHash().put(key, "userId", "123");
            redisUtils.del();
            return redisTemplate.opsForHash().get(key, "loginName");
        } finally {
            redisUtils.expire(key, 10);
        }

    }

}
