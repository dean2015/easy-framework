package com.github.gs618.easy.starter.datasource.annotation;

import java.lang.annotation.*;

/**
 * 自定义Datasource
 *
 * @author s.c.gao
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String value();

}
