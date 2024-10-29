package com.example.runningmanager.service.Impl;

import com.example.runningmanager.dao.dto.ResponseResult;
import com.example.runningmanager.dao.entity.RunningRecord;
import com.example.runningmanager.mapper.RunningMapper;
import com.example.runningmanager.service.RunningService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RunningServiceImpl implements RunningService {
    private RunningMapper runningMapper;
    //创建跑步记录
    public ResponseResult createRunningRecord(RunningRecord runningRecord) {
        int is;
        if(runningRecord != null){
            is= runningMapper.insert(runningRecord);
        }else {
            return new ResponseResult(500, "参数为空", null);
        }
        if(is == 1){
            return new ResponseResult(200, "创建成功", null);
        }else {
            return new ResponseResult(500, "服务端错误", null);
        }
    }

    //根据id查询跑步信息
    public ResponseResult selectByUserId(Integer userId) {
        RunningRecord runningRecord = runningMapper.selectByUserId(userId);
        if(runningRecord != null){
            return new ResponseResult(200, "查询成功", runningRecord);
        }else {
            return new ResponseResult(500, "服务端错误", null);
        }
    }



}
