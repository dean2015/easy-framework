package com.github.gs618.easy.starter.autoboxing;


import com.github.gs618.easy.model.protocol.WebProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author s.c.gao
 */
public class NullHandleFilter extends OncePerRequestFilter {

    @Autowired
    private AutoboxingProperties autoboxingProperties;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestPath = request.getRequestURI();
        return MatcherUtils.ignoreHeader(autoboxingProperties.getHeaders(), request)
                || super.shouldNotFilter(request)
                || MatcherUtils.isUrlIncluded(autoboxingProperties.getPatterns(), requestPath);
    }

    private static final String CHARSET = "utf-8";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ResponseWrapper responseNullWrapper = new ResponseWrapper(response);
        filterChain.doFilter(request, responseNullWrapper);
        byte[] bytes = responseNullWrapper.getBytes();
        if (bytes.length <= 0) {
            bytes = WebProtocol.newInstance().toString().getBytes(Charset.forName(CHARSET));
            response.setCharacterEncoding(CHARSET);
            response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        }
        response.getOutputStream().write(bytes);
    }


}
