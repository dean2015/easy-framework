package com.github.gs618.easy.model.exception;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author s.c.gao
 */
@Data
@Accessors(chain = true)
public class ApplicationException extends RuntimeException {

    protected Integer code;
    protected String message;
    protected String[] parameters;

    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
        this.message = message;
    }

}
