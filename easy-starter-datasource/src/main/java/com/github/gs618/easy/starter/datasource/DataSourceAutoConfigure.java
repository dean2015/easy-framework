package com.github.gs618.easy.starter.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

/**
 * 数据源配置
 *
 * @author gaosong
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(value = {DataSourceProperties.class})
public class DataSourceAutoConfigure {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private DefaultListableBeanFactory beanFactory;

    private void loadDataSources() {
        Map<String, DataSourceConnectionProperties> connectionMap = dataSourceProperties.getDatasource();
        connectionMap.forEach((dsName, properties) -> {
            dsName += dataSourceProperties.getBeanNameSuffix();
            beanFactory.registerBeanDefinition(dsName,
                    BeanDefinitionBuilder
                            .rootBeanDefinition("com.alibaba.druid.pool.DruidDataSource")
                            .setScope("prototype").getBeanDefinition());
            DruidDataSource druidDataSource = beanFactory.getBean(dsName, DruidDataSource.class);
            druidDataSource.setDriverClassName(properties.getDriverClassName());
            druidDataSource.setUrl(properties.getUrl());
            druidDataSource.setUsername(properties.getUsername());
            druidDataSource.setPassword(properties.getPassword());

            // 数据源其他参数配置
            // 配置最大连接
            druidDataSource.setMaxActive(properties.getMaxActive());
            // 配置初始连接
            druidDataSource.setInitialSize(properties.getInitialSize());
            // 配置最小连接
            druidDataSource.setMinIdle(properties.getMinIdle());
            // 连接等待超时时间
            druidDataSource.setMaxWait(properties.getMaxWait());
            // 间隔多久进行检测,关闭空闲连接
            druidDataSource.setTimeBetweenEvictionRunsMillis(
                    properties.getTimeBetweenEvictionRunsMillis());
            // 一个连接最小生存时间
            druidDataSource
                    .setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
            // 用来检测是否有效的sql
            druidDataSource.setValidationQuery(properties.getValidationQuery());
            druidDataSource.setTestWhileIdle(true);
            druidDataSource.setTestOnBorrow(false);
            druidDataSource.setTestOnReturn(false);
            // 打开PSCache,并指定每个连接的PSCache大小
            druidDataSource.setPoolPreparedStatements(true);
            druidDataSource.setMaxOpenPreparedStatements(properties.getMaxOpenPreparedStatements());
            // 配置sql监控的filter
            try {
                druidDataSource.setFilters(properties.getFilters());
            } catch (SQLException e) {
                log.error("加载数据源监控过滤器失败", e);
            }
        });
    }


    /**
     * datasource 调度代理
     */
    @Bean(name = "defaultDataSource")
    @Primary
    public DataSource defaultDataSource() {
        loadDataSources();
        String defaultDataSourceBeanName = DataSourceProperties.DEFAULT + dataSourceProperties.getBeanNameSuffix();
        return beanFactory.getBean(defaultDataSourceBeanName, DruidDataSource.class);
    }

}
