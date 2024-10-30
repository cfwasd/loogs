package com.example.runningmanager.controller;

import com.example.common.ResponseResult;
import com.example.runningmanager.dao.entity.UserChallenge;
import com.example.runningmanager.service.Impl.UserChallengesServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/challenges")
@AllArgsConstructor
public class UserChallengesController {
    private UserChallengesServiceImpl userChallengesService;

    // 用户参与挑战
    @PostMapping("/join")
    public ResponseResult joinChallenge(@RequestBody UserChallenge userChallenge) {
        return userChallengesService.joinChallenge(userChallenge);
    }

    // 查询用户挑战记录
    @PostMapping("/progress/{userId}")
    public ResponseResult selectUserChallenges(@PathVariable("userId") Long userId) {
        return userChallengesService.selectUserChallenges(userId);
    }

    // 更新用户挑战记录
    @PostMapping("/update")
    public ResponseResult updateUserChallenges(@RequestBody UserChallenge userChallenge) {
        return userChallengesService.updateUserChallenges(userChallenge);
    }
}
