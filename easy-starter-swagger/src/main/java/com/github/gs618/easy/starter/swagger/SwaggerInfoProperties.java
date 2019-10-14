package com.github.gs618.easy.starter.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author s.c.gao
 */
@Data
@ConfigurationProperties(prefix = SwaggerInfoProperties.PREFIX)
public class SwaggerInfoProperties {

    public static final String PREFIX = "easy.swagger";

    private String groupName = "Automation";

    private String basePackage = "";

    private String[] basePackages;

    private String author = "s.c.gao";

    private String homepage = "https://#";

    private String email = "";

    private String title = "Easy framework API";

    private String description = "";

    private String version = "1.0.0";

    private String license = "None";

    private String licenseUrl = "";

    private String termsOfServiceUrl = "https://#";

}