package com.example.runningmanager.service;

import com.example.common.ResponseResult;
import com.example.runningmanager.dao.entity.RunningRecord;

//跑步记录服务
public interface RunningService {
    // 创建跑步记录
    public ResponseResult createRunningRecord(RunningRecord runningRecord);

    // 查询用户跑步记录
    public ResponseResult selectByUserId(Integer userId);

    //更新跑步记录
    public ResponseResult updateRunningRecord(RunningRecord runningRecord);

    // 删除跑步记录
    public ResponseResult deleteRunningRecord(Long recordId);
}
