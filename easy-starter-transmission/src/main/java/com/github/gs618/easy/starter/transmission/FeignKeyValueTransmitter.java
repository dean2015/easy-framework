package com.github.gs618.easy.starter.transmission;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.core.annotation.Order;

/**
 * @author gaosong
 */
@Order
public class FeignKeyValueTransmitter implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        KeyValues.transmit(requestTemplate::header);
    }
}
