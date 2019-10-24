package com.github.gs618.easy.starter.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Datasource路由配置
 * 重写determineCurrentLookupKey方法来动态获取指定datasource
 *
 * @author s.c.gao
 */
public class MultipleRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.get();
    }

}
