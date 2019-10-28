package com.github.gs618.easy.starter.transmission;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author s.c.gao
 */
@Data
@ConfigurationProperties(prefix = TransmissionProperties.PREFIX)
public class TransmissionProperties {

    public static final String PREFIX = "easy";

    private Map<String, String> transmission = new LinkedHashMap<>();


}