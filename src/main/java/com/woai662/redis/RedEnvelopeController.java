package com.woai662.redis;

import com.woai662.base.enums.ResponseCode;
import com.woai662.base.response.JsonResult;
import com.woai662.base.util.BaseUtil;
import com.woai662.demo.service.RedEnvelopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/redPoint")
public class RedEnvelopeController {

    @Autowired
    private RedEnvelopeDao redEnvelopeDao;

    @Autowired
    private RedEnvelopeService redEnvelopeService;

    /**
     * 创建一个红包
     * @param totalPoint
     * @param sum
     * @return
     */
    @RequestMapping("/createRedPoint")
    public JsonResult createRedPoint(double totalPoint,int sum) {
        String redEnvelopeId = BaseUtil.getRandomString(10);
        RedEnvelope redEnvelope = new RedEnvelope(redEnvelopeId,totalPoint,sum);
        this.redEnvelopeDao.saveRedPoint(redEnvelope);
        JsonResult res = new JsonResult();
        res.setData(redEnvelope);
        return res;
    }

    @RequestMapping("/getRedPoint")
    public RedEnvelope getRedPoint(String redEnvelopeId) {
        RedEnvelope r = this.redEnvelopeDao.getRedPoint(redEnvelopeId);
        return r;
    }

    /**
     * 一个学生单次抢红包的逻辑
     * http://localhost:8088/redis/redPoint/grapRedPoint_v1?redEnvelopeId=5bJyheP2ah
     *
     * @param redEnvelopeId
     * @return
     */
    @RequestMapping("/grapRedPoint_v1")
    public JsonResult grapRedPoint(String redEnvelopeId) {
        JsonResult jsonResult = new JsonResult();
        int remainPeople = this.redEnvelopeDao.getAuth(redEnvelopeId);
        if (remainPeople < 0) {
            jsonResult.setCode(ResponseCode.SYS_ERROR.getCode());
            jsonResult.setMessage("没有足够的红包数量");
            return jsonResult;
        }
        // 开始抢红包
        int totalPoint = this.redEnvelopeDao.getTotalPoint(redEnvelopeId);
        if (totalPoint < 0) {
            jsonResult.setCode(ResponseCode.SYS_ERROR.getCode());
            jsonResult.setMessage(ResponseCode.SYS_ERROR.getMessage());
            return jsonResult;
        }

        boolean isGrap = this.redEnvelopeService.grapRedEnvelope_v1(redEnvelopeId,totalPoint,remainPeople + 1);
        if (!isGrap) {
            jsonResult.setCode(ResponseCode.SYS_ERROR.getCode());
            jsonResult.setMessage(ResponseCode.SYS_ERROR.getMessage());
            return jsonResult;
        }
        return jsonResult;
    }

}
