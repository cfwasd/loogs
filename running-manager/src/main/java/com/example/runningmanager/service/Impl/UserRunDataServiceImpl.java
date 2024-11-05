package com.example.runningmanager.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.runningmanager.dao.entity.UserRunData;
import com.example.runningmanager.service.UserRunDataService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author wzh
 */
@Service
public class UserRunDataServiceImpl implements UserRunDataService {
    @Override
    public boolean saveBatch(Collection<UserRunData> entityList) {
        return UserRunDataService.super.saveBatch(entityList);
    }

    @Override
    public boolean saveBatch(Collection<UserRunData> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<UserRunData> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<UserRunData> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(UserRunData entity) {
        return false;
    }

    @Override
    public UserRunData getOne(Wrapper<UserRunData> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Optional<UserRunData> getOneOpt(Wrapper<UserRunData> queryWrapper, boolean throwEx) {
        return Optional.empty();
    }

    @Override
    public Map<String, Object> getMap(Wrapper<UserRunData> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<UserRunData> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<UserRunData> getBaseMapper() {
        return null;
    }

    @Override
    public Class<UserRunData> getEntityClass() {
        return null;
    }
}
