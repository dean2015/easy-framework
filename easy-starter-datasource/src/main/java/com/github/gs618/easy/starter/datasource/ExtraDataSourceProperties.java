package com.github.gs618.easy.starter.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author s.c.gao
 */
@ConfigurationProperties(prefix = ExtraDataSourceProperties.PREFIX)
@Data
public class ExtraDataSourceProperties {

    public static final String PREFIX = "spring.datasource";

    private Map<String, DataSourceProperties> extra = new LinkedHashMap<>();

}
