package com.github.gs618.easy.starter.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author s.c.gao
 */
@ConfigurationProperties(prefix = DataSourceProperties.PREFIX)
@Data
public class DataSourceProperties {

    public static final String PREFIX = "spring";

    public static final String DEFAULT = "default";

    private String beanNameSuffix = "Datasource";

    private Map<String, DataSourceConnectionProperties> datasource = new LinkedHashMap<>();

}
