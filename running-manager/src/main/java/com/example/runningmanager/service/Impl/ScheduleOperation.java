package com.example.runningmanager.service.Impl;

import com.example.runningmanager.dao.entity.UserRunData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author wzh
 */
@Component
@Slf4j
public class ScheduleOperation {
    @Resource
    private DataOperation dataOperation;

    @Scheduled(cron = "0 0 0 * * ?")
    public void importData() {
        log.info("定时任务开始....");
        boolean flag;
        try {
            Set<String> keys = dataOperation.getKeys();
            List<UserRunData> list = dataOperation.getData(keys);
            flag = dataOperation.importData(list);
            if (flag) {
                log.info("定时任务成功....");
            } else {
                log.error("定时任务失败....");
            }
        } catch (Exception e) {
            log.error("定时任务发生错误....");
            flag = false;
        }
    }
}
