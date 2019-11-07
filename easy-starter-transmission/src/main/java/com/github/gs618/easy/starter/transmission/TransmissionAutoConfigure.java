package com.github.gs618.easy.starter.transmission;

import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author gaosong
 */
@Configuration
@EnableConfigurationProperties(value = {
        TransmissionProperties.class
})
public class TransmissionAutoConfigure {

    @Bean
    public KeyValuesInitRunner keyValuesInitRunner() {
        return new KeyValuesInitRunner();
    }

    @ConditionalOnClass(Feign.class)
    @Bean
    public FeignKeyValueTransmitter feignKeyValueTransmitter() {
        return new FeignKeyValueTransmitter();
    }

    @ConditionalOnClass(RestTemplate.class)
    @Bean
    public RestTemplateKeyValueTransmitter restTemplateKeyValueTransmitter(@Autowired(required = false) List<RestTemplate> restTemplates) {
        return new RestTemplateKeyValueTransmitter(restTemplates);
    }

    @Bean
    public KeyValuesFilter keyValuesFilter() {
        return new KeyValuesFilter();
    }

    @Bean
    public FilterRegistrationBean keyValuesFilterRegistration() {
        FilterRegistrationBean<KeyValuesFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(keyValuesFilter());
        registration.addUrlPatterns("/*");
        registration.setName("keyValuesFilter");
        registration.setOrder(0);
        return registration;
    }
}
