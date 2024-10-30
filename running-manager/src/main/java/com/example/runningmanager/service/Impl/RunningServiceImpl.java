package com.example.runningmanager.service.Impl;


import com.example.common.ResponseResult;
import com.example.runningmanager.dao.entity.RunningRecord;
import com.example.runningmanager.mapper.RunningMapper;
import com.example.runningmanager.service.RunningService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RunningServiceImpl implements RunningService {
    private RunningMapper runningMapper;
    //创建跑步记录
    @Override
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
    @Override
    public ResponseResult selectByUserId(Integer userId) {
        if(userId == null){
            return new ResponseResult(500, "参数为空", null);
        }
        //获取数据
        List<RunningRecord> runningRecords = runningMapper.selectByUserId(userId);

        if(!runningRecords.isEmpty()){
            return new ResponseResult(200, "查询成功", runningRecords);
        }else {
            return new ResponseResult(500, "数据不存在", null);
        }
    }

    //更新跑步记录
    @Override
    public ResponseResult updateRunningRecord(RunningRecord runningRecord) {
        int is;
        if(runningRecord != null){
            is= runningMapper.updateById(runningRecord);
        }else {
            return new ResponseResult(500, "参数为空", null);
        }
        if(is == 1){
            return new ResponseResult(200, "更新成功", null);
        }else {
            return new ResponseResult(500, "服务端错误", null);
        }
    }

    //删除跑步记录
    @Override
    public ResponseResult deleteRunningRecord(Long recordId) {
        if(recordId != null){
            int is = runningMapper.deleteById(recordId);
            if(is == 1){
                return new ResponseResult(200, "删除成功", null);
            }else {
                return new ResponseResult(500, "服务端错误", null);
            }
        }else {
            return new ResponseResult(500, "参数为空", null);
        }
    }

}



