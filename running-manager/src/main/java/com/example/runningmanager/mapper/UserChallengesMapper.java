package com.example.runningmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.runningmanager.dao.entity.UserChallenge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserChallengesMapper extends BaseMapper<UserChallenge> {
    // 查询用户参与挑战
    @Select("select * from user_challenges where user_id = #{userId}")
    List<UserChallenge> selectUserChallenges(Long userId);
}
