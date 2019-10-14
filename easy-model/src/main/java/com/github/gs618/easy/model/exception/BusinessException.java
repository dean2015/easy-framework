package com.github.gs618.easy.model.exception;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author s.c.gao
 */
@Data
@Accessors(chain = true)
public class BusinessException extends RuntimeException {

    protected Integer code;
    protected String message;
    protected String[] parameters;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
