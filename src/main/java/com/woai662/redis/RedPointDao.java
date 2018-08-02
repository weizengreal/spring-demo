package com.woai662.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class RedPointDao {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 存储红包数据到 Redis 中
     * @param redPoint
     */
    public void saveRedPoint(RedPoint redPoint) {
        try {
            redisTemplate.opsForValue().set(redPoint.getRedPointId() + "_point",(int)redPoint.getTotalPoint() + "");
            redisTemplate.opsForValue().set(redPoint.getRedPointId() + "_count",redPoint.getSum() + "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 获取一个红包数据
     *
     * @param redPointId
     * @return
     */
    public RedPoint getRedPoint(String redPointId) {
        try {
            double totalPoint = Double.parseDouble(redisTemplate.opsForValue().get(redPointId + "_point"));
            int sum = Integer.parseInt(redisTemplate.opsForValue().get(redPointId + "_count"));
            return new RedPoint(redPointId,totalPoint,sum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 获得某个 redPointId 下的总积分
     * @param redPointId
     * @return
     */
    public int getTotalPoint(String redPointId) {
        try {
            return Integer.parseInt(redisTemplate.opsForValue().get(redPointId + "_point"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * 保存红包的总积分
     * @param redPoint
     * @param point
     * @return
     */
    public boolean saveTotalPoint(String redPoint,int point) {
        try {
            redisTemplate.opsForValue().set(redPoint + "_point",point + "");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是否能够抢红包
     * @param redPointId
     * @return
     */
    public int getAuth(String redPointId) {
        return redisTemplate.opsForValue().decrement(redPointId + "_count",1).intValue();
    }

    public boolean decrPoint(String redPointId,int point) {
        long remainPoint = redisTemplate.opsForValue().decrement(redPointId + "_point",point);
        return remainPoint >= 0;
    }

}
