package com.zhanghf.component;

import com.zhanghf.dto.CommonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhanghf
 * @version 1.0
 */
@Slf4j
@Component
public class Scheduler {

    /**
     * 每隔3分钟执行一次
     */
    @Scheduled(fixedRate = 180000)
    public void everyThreeMinutesTasks() {
        SimpleDateFormat dateFormat = CommonDTO.getDateFormat();
        log.info("每三分钟执行一次,date={}", dateFormat.format(new Date()));

    }

    /**
     * 每天凌晨1点执行一次
     */
    @Scheduled(cron = "0 00 01 * * ?")
    public void takeNumberTask() {
        SimpleDateFormat dateFormat = CommonDTO.getDateFormat();
        log.info("凌晨一点执行,date={}", dateFormat.format(new Date()));
    }

    /**
     * 每分钟执行一次
     */
    @Scheduled(fixedRate = 60000)
    public void perMinuteTasks() {
        SimpleDateFormat dateFormat = CommonDTO.getDateFormat();
        log.info("每分钟执行一次,date={}", dateFormat.format(new Date()));

    }

}
