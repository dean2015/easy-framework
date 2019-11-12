package com.github.gs618.easy.starter.datasource;

import com.github.gs618.easy.starter.datasource.annotation.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;


/**
 * 额外数据源选择AOP
 *
 * @author s.c.gao
 */
@Aspect
@Order(-10)
@Slf4j
public class DataSourceOnClassAspect {

    @Before(value = "@within(dataSource)")
    public void setDataSourceType(JoinPoint joinPoint, DataSource dataSource) {
        String dataSourceKey = dataSource.value();
        if (StringUtils.isNotEmpty(dataSourceKey)) {
            DataSourceHolder.set(dataSourceKey);
        } else {
            DataSourceHolder.set(null);
        }
    }

    @After(value = "@within(dataSource)")
    public void clearDataSourceType(JoinPoint joinPoint, DataSource dataSource) {
        DataSourceHolder.remove();
    }

}
