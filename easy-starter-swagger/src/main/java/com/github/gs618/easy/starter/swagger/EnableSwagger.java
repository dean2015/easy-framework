package com.github.gs618.easy.starter.swagger;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportAutoConfiguration(SwaggerAutoConfigure.class)
public @interface EnableSwagger {
}
