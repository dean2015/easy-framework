package com.github.gs618.easy.starter.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 数据源配置
 * 其中固定包含一个主库的Datasource，一个从库的Datasource；
 * 并且可以通过extraDataSourceProvider接口加入指定DataSource
 *
 * @author gaosong
 */
@Configuration
@Slf4j
@EnableConfigurationProperties({DataSourceProperties.class, ExtraDataSourceProperties.class})
public class DataSourceAutoConfigure {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private ExtraDataSourceProperties extraDataSourceProperties;

    private Map<Object, Object> loadDataSources() {
        Map<String, DataSourceProperties> connectionMap = extraDataSourceProperties.getExtra();
        Map<Object, Object> dataSourceMap = new LinkedHashMap<>(connectionMap.size() + 1);
        dataSourceMap.put(DataSourceProperties.DEFAULT, assembleDruidDataSource(dataSourceProperties));
        connectionMap.forEach((dsname, connection) -> {
            dataSourceMap.put(dsname, assembleDruidDataSource(connection));
        });
        return dataSourceMap;
    }

    private DruidDataSource assembleDruidDataSource(DataSourceProperties properties) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setDriverClassName(properties.getDriverClassName());
        datasource.setUrl(properties.getUrl());
        datasource.setUsername(properties.getUsername());
        datasource.setPassword(properties.getPassword());

        // 数据源其他参数配置
        // 配置最大连接
        datasource.setMaxActive(properties.getMaxActive());
        // 配置初始连接
        datasource.setInitialSize(properties.getInitialSize());
        // 配置最小连接
        datasource.setMinIdle(properties.getMinIdle());
        // 连接等待超时时间
        datasource.setMaxWait(properties.getMaxWait());
        // 间隔多久进行检测,关闭空闲连接
        datasource.setTimeBetweenEvictionRunsMillis(
                properties.getTimeBetweenEvictionRunsMillis());
        // 一个连接最小生存时间
        datasource
                .setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        // 用来检测是否有效的sql
        datasource.setValidationQuery(properties.getValidationQuery());
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        // 打开PSCache,并指定每个连接的PSCache大小
        datasource.setPoolPreparedStatements(true);
        datasource.setMaxOpenPreparedStatements(properties.getMaxOpenPreparedStatements());
        // 配置sql监控的filter
        try {
            datasource.setFilters(properties.getFilters());
        } catch (SQLException e) {
            log.error("加载数据源监控过滤器失败", e);
        }
        return datasource;
    }


    /**
     * datasource 调度代理
     */
    @Bean(name = "multipleRoutingDataSource")
    public DataSource multipleRoutingDataSource() {
        Map<Object, Object> targetDataSources = loadDataSources();
        MultipleRoutingDataSource multipleRoutingDataSource = new MultipleRoutingDataSource();
        multipleRoutingDataSource.setDefaultTargetDataSource(targetDataSources.get(DataSourceProperties.DEFAULT));
        multipleRoutingDataSource.setTargetDataSources(targetDataSources);
        return multipleRoutingDataSource;
    }

    @Bean
    public DataSourceOnClassAspect dataSourceOnClassAspect() {
        return new DataSourceOnClassAspect();
    }

    @Bean
    public DataSourceOnMethodAspect dataSourceOnMethodAspect() {
        return new DataSourceOnMethodAspect();
    }
}
