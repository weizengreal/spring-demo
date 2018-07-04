package com.woai662.base.enums;

public enum ResponseCode {
    SUCCESS(200,"success"),

    OT_LOGIN(400, "not login"),

    EXCEPTION(401, "发生异常"),

    SYS_ERROR(402, "系统错误"),

    PARAMS_ERROR(403, "参数错误 "),

    NOT_SUPPORTED(410, "不支持或已经废弃"),

    INVALID_AUTHCODE(444, "无效的AuthCode"),

    TOO_FREQUENT(445, "太频繁的调用"),

    UNKNOWN_ERROR(499, "未知错误");

    private int code;

    private String message;

    private ResponseCode(int code,String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

}

