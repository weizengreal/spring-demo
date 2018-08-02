package com.woai662.demo.service;

import com.woai662.redis.RedPointDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedPointService{

    @Autowired
    private RedPointDao redPointDao;

    public void grapRedPoint(double total_money,int total_people) {
        double minPoint = 0.01;

        for (int i = 0; i < total_people - 1; i++) {
            int j = i + 1;
            double safeMoney = (total_money - (total_people - j) * minPoint) / (total_people - j);
            double tmp_money = (Math.random() * (safeMoney * 100 - minPoint * 100) + minPoint * 100) / 100;
            total_money = total_money - tmp_money;
            System.out.format("第 %d 个红包： %.2f 元，剩下： %.2f 元\n", j, tmp_money, total_money);
        }
        System.out.format("第 %d 个红包： %.2f 元，剩下： 0 元\n", total_people,
                total_money);
    }


    public boolean grapRedPoint_v1(String redPointId,int totalPoint,int totalPeople) {
        int point = this.calculateRedPoint(totalPoint,totalPeople);
        // TODO 将积分数据写入对应用户的数据包中
        this.saveUserInfo();

        // TODO 可能出现幻读情况，需要解决
        return this.redPointDao.saveTotalPoint(redPointId,totalPoint - point);
    }

    /**
     * 将数据存入用户信息记录中
     */
    public void saveUserInfo() {

    }


    /**
     * 实时计算每个人应该抢到多少红包
     * @param totalPoint 红包总积分
     * @param totalPeople 红包总人数
     * @return
     */
    private int calculateRedPoint(int totalPoint,int totalPeople) {
        int minPoint = 1;

        if (totalPeople == 1) {
            return totalPoint;
        }

        double safePoint = (totalPoint - (totalPeople - 1) * minPoint)
                / (totalPeople - 1);
        return  (int)(Math.random()
                * (safePoint * 100 - minPoint * 100) + minPoint * 100) / 100;
    }


}
