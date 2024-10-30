package com.example.runningmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.runningmanager.dao.entity.UserChallenge;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserChallengesMapper extends BaseMapper<UserChallenge> {
    // 查询用户参与挑战
    @Select("select * from user_challenges where user_id = #{userId}")
    List<UserChallenge> selectUserChallenges(Long userId);

    //更新挑战进度
    @Update("update user_challenges set completed_is = #{completedIs}, completed_at = #{completedAt}, current_distance = #{currentDistance}, updated_at = #{updatedAt} where user_challenge_id = #{userChallengeId}")
    int updateUserChallenges(UserChallenge userChallenge);

    // 删除用户挑战
    @Delete("delete from user_challenges where user_id = #{userId} and challenge_id = #{challengeId}")
    int deleteUserChallenges(UserChallenge userChallenge);

    // 插入用户挑战
    @Insert("insert into user_challenges (user_id, challenge_id, joined_at, completed_is, completed_at, current_distance, created_at, updated_at) " +
            "values (#{userId}, #{challengeId}, #{joinedAt}, #{completedIs}, #{completedAt}, #{currentDistance}, #{createdAt}, #{updatedAt})")
    int insertUserChallenges(UserChallenge userChallenge);
}
