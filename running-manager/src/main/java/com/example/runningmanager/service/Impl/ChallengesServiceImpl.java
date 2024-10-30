package com.example.runningmanager.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.ResponseResult;
import com.example.runningmanager.dao.entity.Challenge;
import com.example.runningmanager.mapper.ChallengesMapper;
import com.example.runningmanager.service.ChallengesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChallengesServiceImpl implements ChallengesService {
    private ChallengesMapper challengesMapper;
    //查看所有挑战
    @Override
    public ResponseResult selectChallenges() {
        List<Challenge> challenges = challengesMapper.selectChallenges();
        if (challenges.isEmpty()){
            return new ResponseResult(500, "数据不存在", null);
        }else {
            return new ResponseResult(200, "查询成功", challenges);
        }
    }
}
