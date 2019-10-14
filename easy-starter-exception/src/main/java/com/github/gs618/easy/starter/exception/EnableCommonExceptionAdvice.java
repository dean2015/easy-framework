package com.github.gs618.easy.starter.exception;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportAutoConfiguration(CommonExceptionAdviceAutoConfigure.class)
public @interface EnableCommonExceptionAdvice {
}
