package com.example.runningmanager.service;

import com.example.common.ResponseResult;
import com.example.runningmanager.dao.entity.UserChallenge;

public interface UserChallengesService {
    //用户参加挑战
    public ResponseResult joinChallenge(UserChallenge userChallenge);

    //获取个人挑战记录
    public ResponseResult selectUserChallenges(Long userId);

    //更新个人挑战记录
    public ResponseResult updateUserChallenges(UserChallenge userChallenge);
}
