package com.example.runningmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.runningmanager.dao.entity.RunningRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PostMapping;

@Mapper
public interface RunningMapper extends BaseMapper<RunningRecord> {

    //查询个人跑步记录
    @Select("select * from running_records where user_id = #{userId}")
    public RunningRecord selectByUserId(Integer userId);
}
