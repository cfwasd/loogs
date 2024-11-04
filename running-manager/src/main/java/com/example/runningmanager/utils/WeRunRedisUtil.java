package com.example.runningmanager.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class WeRunRedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 将用户ID、今日的运动步数和是否兑换了里程存入Redis
     *
     * @param userId 用户ID
     * @param maxStepCount 今日的运动步数
     * 0: 存储失败
     * 1: 存储成功
     */
    public int saveUserWeRunData(String userId, String maxStepCount) {
        try {
            Map<Object, Object> userData = getUserWeRunData(userId);
            if (userData.isEmpty()) {
                redisTemplate.opsForHash().put(userId, "maxStepCount", maxStepCount);
                //兑换次数
                redisTemplate.opsForHash().put(userId, "exchangeCount", "0");
                //已兑换步数
                redisTemplate.opsForHash().put(userId, "uesStepCount", "0");
                return 1;
            } else {
                redisTemplate.opsForHash().put(userId, "maxStepCount", maxStepCount);
                return 1;
            }
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * 获取用户今日的运动数据
     *
     * @param userId 用户ID
     * @return 用户数据
     */
    public Map<Object, Object> getUserWeRunData(String userId) {
        return redisTemplate.opsForHash().entries(userId);
    }

    /**
     * 用户兑换里程
     * @param userId 用户ID
     * @return 兑换结果
     * 200: 兑换成功
     * 0: 用户数据不存在
     * 1: 运动步数不足
     */
    public int exchange(String userId,String stepCount){
        Map<Object, Object> userData = getUserWeRunData(userId);
        if(userData.isEmpty()){
            return 0;
        }
        //获取用户运动步数和兑换次数
        Integer maxStepCount = Integer.parseInt(userData.get("maxStepCount").toString());
        Integer exchangeCount = Integer.parseInt(userData.get("exchangeCount").toString());
        //判断运动步数是否足够兑换里程
        if(maxStepCount >= Integer.parseInt(stepCount)){
            //更新用户运动里程和兑换次数
            Integer totalMileage  = Integer.parseInt(userData.get("totalMileage").toString()) + Integer.parseInt(stepCount)*10;
            //更新用户已兑换步数
            redisTemplate.opsForHash().put(userId, "uesStepCount", stepCount);
            //更新兑换次数
            redisTemplate.opsForHash().put(userId, "exchangeCount", String.valueOf(exchangeCount+1));
            //更新用户总里程
            redisTemplate.opsForHash().put(userId,"totalMileage", String.valueOf(totalMileage));
            return 200;
        }else {
            return 1;
        }
    }
    /**
     * 存储用户总里程和积累天数
     * @param userId 用户ID
     * @param totalMileage 总里程
     * @param day 积累天数
     */
    public void saveTotalMileageAndDay(String userId, String totalMileage, String day) {
        redisTemplate.opsForHash().put(userId, "totalMileage", totalMileage);
        redisTemplate.opsForHash().put(userId, "day", day);
    }

    /**
     * 设置数据的过期时间
     *
     * @param userId 用户ID
     * @param expirationTime 过期时间（秒）
     */
    public void setExpirationTime(String userId, long expirationTime) {
        String key = "user:" + userId + ":werun";
        redisTemplate.expire(key, expirationTime, TimeUnit.SECONDS);
    }
}