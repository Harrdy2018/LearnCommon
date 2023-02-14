package com.oppo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException{
    /**
     * code
     */
    private Integer code;

    /**
     * message
     */
    private String message;

    public BusinessException() {
        super();
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public BusinessException(Throwable cause) {
        this(null, cause.getMessage(), null);
    }

    public BusinessException(Integer code, String message) {
        this(code, message, null);
    }

    public BusinessException(Integer code, Throwable cause) {
        this(code, cause.getMessage(), cause);
    }
}
