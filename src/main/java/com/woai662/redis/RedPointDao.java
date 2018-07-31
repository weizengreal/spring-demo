package com.woai662.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class RedPointDao {

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<Object,Object> valueOperations;

    public void save(RedPoint redPoint) {
        valueOperations.set(redPoint.getRedPointId(),redPoint);
    }

    public RedPoint get(String redPointId) {
        return (RedPoint) valueOperations.get(redPointId);
    }

}
