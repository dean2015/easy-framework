package com.github.gs618.easy.starter.exception;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author s.c.gao
 */
@Configuration
public class CommonExceptionAdviceAutoConfigure {

    @ConditionalOnMissingBean(CustomHandlerExceptionResolver.class)
    @Bean
    public CommonExceptionAdvice getCommonExceptionAdvice() {
        return new CommonExceptionAdvice();
    }

}
