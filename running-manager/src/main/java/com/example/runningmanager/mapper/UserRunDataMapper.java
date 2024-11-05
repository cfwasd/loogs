package com.example.runningmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.runningmanager.dao.entity.UserRunData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wzh
 */
@Mapper
public interface UserRunDataMapper extends BaseMapper<UserRunData> {
    List<UserRunData> selectByMillage();
}
