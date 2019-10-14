package com.github.gs618.easy.starter.autoboxing;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author s.c.gao
 */
@ConfigurationProperties(prefix = AutoboxingProperties.PREFIX)
@Data
public class AutoboxingProperties {

    public static final String PREFIX = "easy.autoboxing.ignore";

    /**
     * "/swagger-resources/**",
     * "/swagger-ui.html",
     * "/v2/api-docs",
     * "/webjars/**",
     * "/",
     * "csrf"
     */
    private List<String> patterns = new ArrayList<>(5);

    /**
     * USING_FEIGN
     */
    private List<String> headers = new ArrayList<>(5);
}
