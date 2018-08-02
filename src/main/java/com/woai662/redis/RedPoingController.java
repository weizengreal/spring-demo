package com.woai662.redis;

import com.woai662.base.enums.ResponseCode;
import com.woai662.base.response.JsonResult;
import com.woai662.base.util.BaseUtil;
import com.woai662.demo.service.RedPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/redPoint")
public class RedPoingController {

    @Autowired
    private RedPointDao redPointDao;

    @Autowired
    private RedPointService redPointService;

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
        this.redPointDao.saveRedPoint(redPoint);
        JsonResult res = new JsonResult();
        res.setData(redPoint);
        return res;
    }

    @RequestMapping("/getRedPoint")
    public RedPoint getRedPoint(String redPointId) {
        RedPoint r = this.redPointDao.getRedPoint(redPointId);
        return r;
    }

    /**
     * 一个学生单次抢红包的逻辑
     * @param redPointId
     * @return
     */
    @RequestMapping("/grapRedPoint_v1")
    public JsonResult grapRedPoint(String redPointId) {
        JsonResult jsonResult = new JsonResult();
        int remainPeople = this.redPointDao.getAuth(redPointId);
        if (remainPeople < 0) {
            jsonResult.setCode(ResponseCode.SYS_ERROR.getCode());
            jsonResult.setMessage("没有足够的红包数量");
            return jsonResult;
        }
        // 开始抢红包
        int totalPoint = this.redPointDao.getTotalPoint(redPointId);
        if (totalPoint < 0) {
            jsonResult.setCode(ResponseCode.SYS_ERROR.getCode());
            jsonResult.setMessage(ResponseCode.SYS_ERROR.getMessage());
            return jsonResult;
        }

        boolean isGrap = this.redPointService.grapRedPoint_v1(redPointId,totalPoint,remainPeople + 1);
        if (!isGrap) {
            jsonResult.setCode(ResponseCode.SYS_ERROR.getCode());
            jsonResult.setMessage(ResponseCode.SYS_ERROR.getMessage());
            return jsonResult;
        }
        return jsonResult;
    }

}
