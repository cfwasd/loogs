package com.example.runningmanager.service.Impl;


import com.example.common.HttpStateCode;
import com.example.common.ResponseResult;
import com.example.common.UserNewRunDataVO;
import com.example.common.stepCountVO;
import com.example.runningmanager.dao.entity.RunningRecord;
import com.example.runningmanager.dao.entity.UserRunData;
import com.example.runningmanager.mapper.RunningMapper;
import com.example.runningmanager.service.RunningService;
import com.example.runningmanager.utils.WeRunDecryptUtil;
import com.example.runningmanager.utils.WeRunRedisUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RunningServiceImpl implements RunningService {
    private RunningMapper runningMapper;
    private WeRunRedisUtil weRunRedisUtil;
    //创建跑步记录
    @Override
    public ResponseResult createRunningRecord(RunningRecord runningRecord) {
        int is;
        if(runningRecord != null){
            is= runningMapper.insertRunningRecord(runningRecord);
        }else {
            return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "参数为空", null);
        }
        if(is == 1){
            return new ResponseResult(HttpStateCode.OK.getCode(), "创建成功", null);
        }else {
            return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "服务端错误", null);
        }
    }

    //根据id查询跑步信息
    @Override
    public ResponseResult selectByUserId(Integer userId) {
        if(userId == null){
            return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "参数为空", null);
        }
        //获取数据
        List<RunningRecord> runningRecords = runningMapper.selectByUserId(userId);

        if(!runningRecords.isEmpty()){
            return new ResponseResult(HttpStateCode.OK.getCode(), "查询成功", runningRecords);
        }else {
            return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "数据不存在", null);
        }
    }

    //更新跑步记录
    @Override
    public ResponseResult updateRunningRecord(RunningRecord runningRecord) {
        int is;
        if(runningRecord != null){
            is= runningMapper.updateRunningRecord(runningRecord);
        }else {
            return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "参数为空", null);
        }
        if(is == 1){
            return new ResponseResult(HttpStateCode.OK.getCode(), "更新成功", null);
        }else {
            return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "服务端错误", null);
        }
    }

    //删除跑步记录
    @Override
    public ResponseResult deleteRunningRecord(Long recordId) {
        if(recordId != null){
            int is = runningMapper.deleteRunningRecord(recordId);
            if(is == 1){
                return new ResponseResult(HttpStateCode.OK.getCode(), "删除成功", null);
            }else {
                return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "服务端错误", null);
            }
        }else {
            return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "参数为空", null);
        }
    }

    //获取微信步数
    @Override
    public ResponseResult getWxStep(stepCountVO stepCountVO) {
        WeRunDecryptUtil weRunDecryptUtil = new WeRunDecryptUtil();
        try {
            String sessionKey =weRunDecryptUtil.getSessionKey(stepCountVO.getCode());
            Map<String, Object> decryptData = weRunDecryptUtil.decryptData(stepCountVO.getEncryptedData(), stepCountVO.getIv(), sessionKey);
            if(decryptData != null){
                return new ResponseResult(HttpStateCode.OK.getCode(), "查询成功", decryptData);
            }else {
                return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "服务端错误", null);
            }
        } catch (Exception e) {
            return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "服务端异常，请联系管理员", null);
        }
    }

    //存储今日运动信
    @Override
    public ResponseResult saveTodayStep(UserNewRunDataVO userNewRunDataVO) {
        int i = weRunRedisUtil.saveUserWeRunData(userNewRunDataVO.getUserId(), userNewRunDataVO.getStepCount());
        if(i == 1){
            return new ResponseResult(HttpStateCode.OK.getCode(), "存储成功", null);
        }else {
            return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "服务端错误", null);
        }
    }

    //查询运动信息
    @Override
    public ResponseResult selectByUserIdAndDay(Integer userId) {
        if(userId == null){
            return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "参数为空", null);
        }
        //获取redis中的用户数据
        Map<Object,Object> userNewData  = weRunRedisUtil.getUserWeRunData(String.valueOf(userId));
        if(userNewData != null){
            //判断数据是否在redis中
            if(userNewData.get("totalMileage")!= null){
                return new ResponseResult(HttpStateCode.OK.getCode(), "查询成功", userNewData);
            }else {
                //如果没有添加数据到redis并返回
                UserRunData userRunData = runningMapper.selectUserRunData(userId);
                weRunRedisUtil.saveTotalMileageAndDay(String.valueOf(userId),String.valueOf(userRunData.getMileage()),String.valueOf(userRunData.getDay()));
                userNewData = weRunRedisUtil.getUserWeRunData(String.valueOf(userId));
                return new ResponseResult(HttpStateCode.OK.getCode(), "查询成功", userNewData);
            }
        }
        return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "服务端错误", null);
    }

    //兑换里程
    @Override
    public ResponseResult exchangeMileage(String userId,String redeemable) {
        if (userId != null && redeemable != null){
            int i = weRunRedisUtil.exchange(userId,redeemable);
            if(i==200){
                return new ResponseResult(HttpStateCode.OK.getCode(), "兑换成功", null);
            }
            if (i==1){
                return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "兑换失败，运动步数不足", null);
            }
            if (i==0){
                return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "兑换失败，用户不存在", null);
            }
        }else {
            return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "参数为空", null);
        }
        return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "兑换失败，服务端错误", null);
    }




}



