package com.github.gs618.easy.starter.datasource;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportAutoConfiguration({DataSourceAutoConfigure.class, TransactionManagerAutoConfigure.class})
public @interface EnableDataSource {
}
