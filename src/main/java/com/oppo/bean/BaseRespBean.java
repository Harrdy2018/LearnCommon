package com.oppo.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRespBean<T> {
    private Integer code;

    private String errorMessage;

    private T data;

    public BaseRespBean() {
    }

    /**
     * 成功构造器
     *
     * @param code code
     * @param data data
     */
    public BaseRespBean(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    /**
     * 失败构造器
     * @param code code
     * @param errorMessage errorMessage
     */
    public BaseRespBean(Integer code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    /**
     * 失败构造器
     * @param code code
     * @param errorMessage errorMessage
     * @param data data
     */
    public BaseRespBean(Integer code, String errorMessage, T data) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public static <T> BaseRespBean success(T data) {
        BaseRespBean baseRespBean = new BaseRespBean();
        baseRespBean.setCode(RetCode.SUCCESS.getCode());
        baseRespBean.setData(data);
        return baseRespBean;
    }
}