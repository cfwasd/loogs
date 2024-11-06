package com.example.runningmanager.controller;

import com.example.common.HttpStateCode;
import com.example.common.ResponseResult;
import com.example.runningmanager.dao.entity.UserRunData;
import com.example.runningmanager.service.Impl.DataOperation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wzh
 */
@RestController
public class UserRunDataController {
    @Resource
    private DataOperation dataOperation;

    @GetMapping("/sort/{count}")
    public ResponseResult sort(@PathVariable(value = "count",required = false) int count) {
        List<UserRunData> list = dataOperation.selectData(count);
        return new ResponseResult(HttpStateCode.OK.getCode(), "获取成功",list);
    }
}
