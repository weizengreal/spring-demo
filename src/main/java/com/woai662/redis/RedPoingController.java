package com.woai662.redis;

import com.woai662.base.response.JsonResult;
import com.woai662.base.util.BaseUtil;
import com.woai662.redis.RedPoint;
import com.woai662.redis.RedPointDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/redPoint")
public class RedPoingController {

    @Autowired
    RedPointDao redPointDao;

    /**
     * 创建一个红包
     * @param totalPoint
     * @param sum
     * @return
     */
    @RequestMapping("/createRedPoint")
    public JsonResult createRedPoint(double totalPoint,int sum) {
        String redPointId = BaseUtil.getRandomString(10);
        RedPoint redPoint = new RedPoint(redPointId,totalPoint,sum);
        this.redPointDao.save(redPoint);
        JsonResult res = new JsonResult();
        res.setData(redPoint);
        return res;
    }

    @RequestMapping("/getRedPoint")
    public RedPoint getRedPoint(String redPointId) {
        return this.redPointDao.get(redPointId);
    }
}
