package com.github.gs618.easy.starter.transmission;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportAutoConfiguration(TransmissionAutoConfigure.class)
public @interface EnableTransmission {
}
