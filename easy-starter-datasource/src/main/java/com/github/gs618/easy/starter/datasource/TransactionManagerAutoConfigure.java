package com.github.gs618.easy.starter.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 事务管理器配置
 *
 * @author s.c.gao
 */
@Configuration
@EnableTransactionManagement
@AutoConfigureAfter(DataSourceAutoConfigure.class)
public class TransactionManagerAutoConfigure extends DataSourceTransactionManagerAutoConfiguration {

    @Autowired
    @Qualifier("multipleRoutingDataSource")
    private DataSource proxyDataSource;

    @Bean(name = "customDataSourceTransactionManager")
    public DataSourceTransactionManager customDataSourceTransactionManager() {
        return new DataSourceTransactionManager(proxyDataSource);
    }

}