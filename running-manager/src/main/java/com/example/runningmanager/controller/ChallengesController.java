package com.example.runningmanager.controller;

import com.example.common.ResponseResult;
import com.example.runningmanager.service.ChallengesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ChallengesController {
    private ChallengesService challengesService;
    //查询所有挑战
    @GetMapping("/challenges")
    public ResponseResult selectChallenges(){
        return challengesService.selectChallenges();
    }

}
