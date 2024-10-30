package com.example.runningmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.runningmanager.dao.entity.Challenge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChallengesMapper extends BaseMapper<Challenge> {
    //查询全部挑战
    @Select("select * from challenges")
    public List<Challenge> selectChallenges();

}
