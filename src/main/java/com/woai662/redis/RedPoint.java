package com.woai662.redis;

import java.io.Serializable;

public class RedPoint implements Serializable {

    // 红包id，唯一标记一次发红包事件
    private String redPointId;

    // 发红包的总积分数量
    private double totalPoint;

    // 红包的总个数
    private int sum;

    public RedPoint(String redPointId,double totalPoint,int sum) {
        this.redPointId = redPointId;
        this.totalPoint = totalPoint;
        this.sum = sum;
    }

    public String getRedPointId() {
        return redPointId;
    }

    public double getTotalPoint() {
        return totalPoint;
    }

    public void setRedPointId(String redPointId) {
        this.redPointId = redPointId;
    }

    public void setTotalPoint(double totalPoint) {
        this.totalPoint = totalPoint;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }
}
