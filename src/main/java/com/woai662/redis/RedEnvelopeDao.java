package com.woai662.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class RedEnvelopeDao {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 存储红包数据到 Redis 中
     * @param redEnvelope
     */
    public void saveRedPoint(RedEnvelope redEnvelope) {
        try {
            redisTemplate.opsForValue().set(redEnvelope.getRedEnvelopeId() + "_point",(int) redEnvelope.getTotalPoint() + "");
            redisTemplate.opsForValue().set(redEnvelope.getRedEnvelopeId() + "_count", redEnvelope.getSum() + "");
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    /**
     * 获取一个红包数据
     *
     * @param redEnvelopeId
     * @return
     */
    public RedEnvelope getRedPoint(String redEnvelopeId) {
        try {
            double totalPoint = Double.parseDouble(redisTemplate.opsForValue().get(redEnvelopeId + "_point"));
            int sum = Integer.parseInt(redisTemplate.opsForValue().get(redEnvelopeId + "_count"));
            return new RedEnvelope(redEnvelopeId,totalPoint,sum);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    /**
     * 获得某个 redEnvelopeId 下的总积分
     * @param redEnvelopeId
     * @return
     */
    public int getTotalPoint(String redEnvelopeId) {
        try {
            return Integer.parseInt(redisTemplate.opsForValue().get(redEnvelopeId + "_point"));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
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
            System.out.println(e.getStackTrace());
            return false;
        }
    }

    /**
     * 判断是否能够抢红包
     * @param redEnvelopeId
     * @return
     */
    public int getAuth(String redEnvelopeId) {
        return redisTemplate.opsForValue().decrement(redEnvelopeId + "_count",1).intValue();
    }

    public boolean decrPoint(String redEnvelopeId,int point) {
        long remainPoint = redisTemplate.opsForValue().decrement(redEnvelopeId + "_point",point);
        return remainPoint >= 0;
    }

}
