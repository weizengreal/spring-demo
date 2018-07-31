package com.woai662.base.response;

import com.woai662.base.enums.ResponseCode;

import java.io.Serializable;

public class JsonResult implements Serializable{

    private int code;

    private String message;

    private Object data;

    public JsonResult() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMessage();
    }

    public JsonResult(ResponseCode resCode) {
        this.code = resCode.getCode();
        this.message = resCode.getMessage();
    }

    public JsonResult(ResponseCode resCode,String message) {
        this.code = resCode.getCode();
        this.message = message;
    }

    public JsonResult(ResponseCode resCode,String message,Object data) {
        this.code = resCode.getCode();
        this.message = message;
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
