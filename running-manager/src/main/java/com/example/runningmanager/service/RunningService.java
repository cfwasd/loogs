package com.example.runningmanager.service;

import com.example.common.ResponseResult;
import com.example.common.UserNewRunDataVO;
import com.example.common.stepCountVO;
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

    //获取微信步数
    public ResponseResult getWxStep(stepCountVO stepCountVO);

    //存储今日运动信息
    public ResponseResult saveTodayStep(UserNewRunDataVO userNewRunDataVO);

    //查询运动信息
    public ResponseResult selectByUserIdAndDay(Integer userId);

    //兑换里程
    public ResponseResult exchangeMileage(String userId,String redeemable);
}
