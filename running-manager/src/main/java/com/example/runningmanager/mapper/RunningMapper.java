package com.example.runningmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.runningmanager.dao.entity.RunningRecord;
import com.example.runningmanager.dao.entity.UserRunData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface RunningMapper extends BaseMapper<RunningRecord> {

    //查询个人跑步记录
    @Select("select * from running_records where user_id = #{userId}")
    public List<RunningRecord> selectByUserId(Integer userId);

    //添加跑步记录
    @Insert("insert into running_records (user_id, date, distance, duration, start_location_lat, start_location_lon, end_location_lat, end_location_lon, average_speed, created_at, updated_at) " +
            "values (#{userId}, #{date}, #{distance}, #{duration}, #{startLocationLat}, #{startLocationLon}, #{endLocationLat}, #{endLocationLon}, #{averageSpeed}, #{createdAt}, #{updatedAt})")
    public int insertRunningRecord(RunningRecord runningRecord);

    //更新跑步记录
    @Insert("update running_records set date = #{date}, distance = #{distance}, duration = #{duration}, start_location_lat = #{startLocationLat}, start_location_lon = #{startLocationLon}, end_location_lat = #{endLocationLat}, end_location_lon = #{endLocationLon}, average_speed = #{averageSpeed}, updated_at = #{updatedAt} where record_id = #{recordId}")
    public int updateRunningRecord(RunningRecord runningRecord);

    //删除跑步记录
    @Insert("delete from running_records where record_id = #{recordId}")
    public int deleteRunningRecord(Long recordId);

    //查询用户运动信息
    @Select("select * from user_run_data where user_id = #{userId}")
    public UserRunData selectUserRunData(Integer userId);
}
