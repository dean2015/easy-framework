package com.github.gs618.easy.starter.exception;

import com.github.gs618.easy.model.exception.ApplicationException;
import com.github.gs618.easy.model.exception.BusinessException;
import com.github.gs618.easy.model.protocol.I18nData;
import com.github.gs618.easy.model.protocol.WebProtocol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author s.c.gao
 */
@RestControllerAdvice(annotations = {RestController.class})
@ResponseBody
@Slf4j
public class CommonExceptionAdvice implements Ordered {

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public WebProtocol<I18nData> handleBusinessException(BusinessException e) {
        log.error("未处理的业务异常 [" + e.getMessage() + "]", e);
        return WebProtocol.<I18nData>newInstance()
                .setCode(e.getCode())
                .setData(I18nData.newInstance()
                        .setMessage(e.getMessage())
                        .setParameters(e.getParameters()));
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ApplicationException.class)
    public WebProtocol<I18nData> handleApplicationException(ApplicationException e) {
        log.error("未处理的非业务异常 [" + e.getMessage() + "]", e);
        return WebProtocol.<I18nData>newInstance()
                .setCode(e.getCode())
                .setData(I18nData.newInstance()
                        .setMessage(e.getMessage())
                        .setParameters(e.getParameters()));
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public WebProtocol<I18nData> handleThrowable(Throwable e) {
        log.error("服务器未知异常", e);
        return WebProtocol.<I18nData>newInstance()
                .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setData(I18nData.newInstance()
                        .setMessage(e.getMessage()));
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}