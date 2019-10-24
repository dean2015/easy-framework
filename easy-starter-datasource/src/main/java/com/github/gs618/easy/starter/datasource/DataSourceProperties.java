package com.github.gs618.easy.starter.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author s.c.gao
 */
@ConfigurationProperties(prefix = DataSourceProperties.PREFIX)
@Data
public class DataSourceProperties {

    public static final String PREFIX = "spring.datasource";

    public static final String DEFAULT = "default";

    private String url;

    private String username;

    private String password;

    private String driverClassName = "com.mysql.cj.jdbc.Driver";

    private String type = "com.alibaba.druid.pool.DruidDataSource";

    private int maxActive = 50;

    private int initialSize = 1;

    private int minIdle = 1;

    private int maxWait = 60000;

    private int timeBetweenEvictionRunsMillis = 60000;

    private int minEvictableIdleTimeMillis = 300000;

    private int maxOpenPreparedStatements = 20;

    private String validationQuery = "select 1";

    private String filters = "stat,config";

}
