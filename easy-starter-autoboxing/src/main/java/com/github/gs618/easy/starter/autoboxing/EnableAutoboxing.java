package com.github.gs618.easy.starter.autoboxing;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportAutoConfiguration(AutoboxingAutoConfigure.class)
public @interface EnableAutoboxing {
}
