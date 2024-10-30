package com.example.runningmanager.controller;

import com.example.runningmanager.dao.entity.RunningRecord;
import com.example.runningmanager.service.Impl.RunningServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.common.ResponseResult;

@RestController
@AllArgsConstructor
public class RunningController {
    private RunningServiceImpl runningService;
    //创建跑步记录
    @PostMapping("/records")
    public ResponseResult createRunningRecord(@RequestBody RunningRecord runningRecord){
        return runningService.createRunningRecord(runningRecord);
    }
    //根据id获取跑步记录
    @GetMapping("/getUserRecords/{userId}")
    public ResponseResult selectByUserId(@PathVariable("userId") Integer userId){
        return runningService.selectByUserId(userId);
    }
}

