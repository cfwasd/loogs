package com.example.runningmanager.service.Impl;

import com.example.common.HttpStateCode;
import com.example.common.ResponseResult;
import com.example.runningmanager.dao.entity.UserChallenge;
import com.example.runningmanager.mapper.UserChallengesMapper;
import com.example.runningmanager.service.UserChallengesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserChallengesServiceImpl implements UserChallengesService {

    private UserChallengesMapper userChallengesMapper;
    // TODO: 2023/4/14
    // 实现用户添加挑战
    @Override
    public ResponseResult joinChallenge(UserChallenge userChallenge) {
        if(userChallenge == null){
            return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "参数错误", null);
        }else {
            int is=0;
            try {
                is = userChallengesMapper.insertUserChallenges(userChallenge);
                if(is == 1){
                    return new ResponseResult(HttpStateCode.OK.getCode(), "添加成功", null);
                }else {
                    return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "添加失败", null);
                }
            }catch (Exception e){
                return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "服务端错误", null);
            }
        }
    }

    // TODO: 2023/4/14
    // 实现用户挑战查询
    @Override
    public ResponseResult selectUserChallenges(Long userId) {
        if(userId != null){
            List<UserChallenge> userChallenges = userChallengesMapper.selectUserChallenges(userId);
            if(!userChallenges.isEmpty()){
                return new ResponseResult(HttpStateCode.OK.getCode(), "查询成功", userChallenges);
            }else {
                return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "数据不存在", null);
            }
        }else {
            return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "参数错误", null);
        }
    }

    // TODO: 2023/4/14
    // 实现用户挑战更新
    @Override
    public ResponseResult updateUserChallenges(UserChallenge userChallenge) {
        if(userChallenge != null){
            int is;
            try {
                is = userChallengesMapper.updateUserChallenges(userChallenge);
                if(is == 1){
                    return new ResponseResult(HttpStateCode.OK.getCode(), "更新成功", null);
                }else {
                    return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "更新失败", null);
                }
            }catch (Exception e){
                return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "服务端错误", null);
            }
        }else {
            return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "参数错误", null);
        }
    }

}
