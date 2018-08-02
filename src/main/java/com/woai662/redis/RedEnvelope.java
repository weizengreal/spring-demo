package com.woai662.redis;

import java.io.Serializable;

public class RedEnvelope implements Serializable {

    // 红包id，唯一标记一次发红包事件
    private String redEnvelopeId;

    // 发红包的总积分数量
    private double totalPoint;

    // 红包的总个数
    private int sum;

    public RedEnvelope(String redEnvelopeId, double totalPoint, int sum) {
        this.redEnvelopeId = redEnvelopeId;
        this.totalPoint = totalPoint;
        this.sum = sum;
    }

    public String getRedEnvelopeId() {
        return redEnvelopeId;
    }

    public double getTotalPoint() {
        return totalPoint;
    }

    public void setRedEnvelopeId(String redEnvelopeId) {
        this.redEnvelopeId = redEnvelopeId;
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
