package com.example.runningmanager.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.runningmanager.dao.dto.UserRunDataModel;
import com.example.runningmanager.dao.entity.UserRunData;
import com.example.runningmanager.mapper.UserRunDataMapper;
import com.example.runningmanager.service.UserRunDataService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author wzh
 */
@Component
public class DataOperation {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserRunDataMapper userRunDataMapper;

    @Resource
    private UserRunDataService userRunDataService;

    public Set<String> getKeys(){
       return redisTemplate.keys("*");
    }

    public List<UserRunData> getData(Set<String> set){
        List<UserRunData> list = new ArrayList<>();
        for (String key : set) {
            UserRunDataModel model = (UserRunDataModel) redisTemplate.opsForValue().get(key);
            UserRunData userRunData = new UserRunData(Integer.parseInt(model.getUserId())
                    ,Integer.parseInt(model.getTotalMileage()),Integer.parseInt(model.getDay()));
            list.add(userRunData);
        }
        return list;
    }

    public String setData(UserRunDataModel model){
        if (model == null){
            return "400"; // 参数为空
        }
        try{
            redisTemplate.opsForValue().set(model.getUserId(),model);
            return "200"; //  存储成功
        }catch (Exception e){
            return "500"; //  存储失败
        }
    }

    public boolean importData(List<UserRunData> list){
        try {
            userRunDataService.saveBatch(list);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<UserRunData> selectData(){
        return userRunDataMapper.selectByMillage();
    }
}
