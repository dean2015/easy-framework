package com.github.gs618.easy.starter.transmission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @author s.c.gao
 */
public class KeyValuesInitRunner implements ApplicationRunner {

    @Autowired
    private TransmissionProperties transmissionProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        KeyValues.putAll(transmissionProperties.getTransmission());
    }
}
