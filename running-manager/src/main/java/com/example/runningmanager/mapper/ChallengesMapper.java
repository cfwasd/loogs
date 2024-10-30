package com.example.runningmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.runningmanager.dao.entity.Challenge;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChallengesMapper extends BaseMapper<Challenge> {
}
