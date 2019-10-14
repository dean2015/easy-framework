package com.github.gs618.easy.starter.autoboxing;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author s.c.gao
 */
@Configuration
@EnableConfigurationProperties(value = {AutoboxingProperties.class})
public class AutoboxingAutoConfigure {

    @Bean
    public ResponseAdvice getResponseAdvisor() {
        return new ResponseAdvice();
    }

    @Bean
    public NullHandleFilter nullHandleFilter() {
        return new NullHandleFilter();
    }

    @Bean
    public FilterRegistrationBean nullHandleFilterRegistration() {
        FilterRegistrationBean<NullHandleFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(nullHandleFilter());
        registration.addUrlPatterns("/*");
        registration.setName("nullHandleFilter");
        registration.setOrder(0x1000);
        return registration;
    }
}