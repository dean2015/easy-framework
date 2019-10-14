package com.github.gs618.easy.starter.autoboxing;

import com.github.gs618.easy.model.protocol.WebProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author s.c.gao
 */
@RestControllerAdvice(annotations = {RestController.class})
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private AutoboxingProperties autoboxingProperties;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    protected PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Object beforeBodyWrite(final Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (MatcherUtils.ignoreHeader(autoboxingProperties.getHeaders(), request)) {
            return body;
        }
        String requestPath = request.getURI().getPath();
        if (MatcherUtils
                .isUrlIncluded(autoboxingProperties.getDefaultPatterns(), requestPath) ||
                MatcherUtils
                        .isUrlIncluded(autoboxingProperties.getPatterns(), requestPath)) {
            return body;
        }
        if (body instanceof WebProtocol) {
            return body;
        }
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        WebProtocol<Object> result = WebProtocol.newInstance()
                .setData(body)
                .setCode(HttpStatus.OK.value())
                .setSuccess(true);
        if (body instanceof String) {
            //同时只能返回字符串,否则会抛出异常ExecutionResult cannot be cast to java.lang.String
            return result.toString();
        }
        return result;
    }
}