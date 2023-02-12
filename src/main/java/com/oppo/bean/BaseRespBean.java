package com.oppo.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRespBean<T> {
    private Integer code;
    private String message;
    private T data;

    public BaseRespBean() {
    }

    public BaseRespBean(StatusCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public static BaseRespBean success() {
        BaseRespBean baseRespBean = new BaseRespBean();
        baseRespBean.setStatusCode(StatusCode.SUCCESS);
        return baseRespBean;
    }

    public static <T> BaseRespBean success(T data) {
        BaseRespBean baseRespBean = new BaseRespBean();
        baseRespBean.setStatusCode(StatusCode.SUCCESS);
        baseRespBean.setData(data);
        return baseRespBean;
    }

    public static BaseRespBean failure(StatusCode statusCode) {
        BaseRespBean baseRespBean = new BaseRespBean();
        baseRespBean.setStatusCode(statusCode);
        return baseRespBean;
    }

    public static <T> BaseRespBean failure(StatusCode statusCode, T data) {
        BaseRespBean baseRespBean = new BaseRespBean();
        baseRespBean.setStatusCode(statusCode);
        baseRespBean.setData(data);
        return baseRespBean;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }
}