package com.oppo.bean;

import lombok.Getter;

@Getter
public enum RetCode {
    // 成功状态码
    SUCCESS(200,"SUCCESS"),
    SYSTEM_ERROR(600, "SYSTEM_ERROR"),

    // 参数错误 1001~1999
    PARAM_IS_INVALID(1001, "PARAM_IS_INVALID"),
    PARAM_IS_BLANK(1002, "PARAM_IS_BLANK"),
    PARAM_TYPE_BIND_ERROR(1003, "PARAM_TYPE_BIND_ERROR"),
    PARAM_NOT_COMPLETE(1004, "PARAM_NOT_COMPLETE"),
    REQUEST_METHOD_INVALID(1005, "REQUEST_METHOD_INVALID"),

    // 用户错误 2001~2999
    USER_NOT_LOGGED_IN(2001, "USER_NOT_LOGGED_IN"),
    USER_LOGIN_ERROR(2002, "USER_LOGIN_ERROR"),
    USER_ACCOUNT_FORBIDDEN(2003, "USER_ACCOUNT_FORBIDDEN"),
    USER_NOT_EXIST(2004, "USER_NOT_EXIST"),
    USER_HAS_EXISTED(2005, "USER_HAS_EXISTED")
    ;

    private Integer code;
    private String message;

    RetCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
