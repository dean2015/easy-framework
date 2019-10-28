package com.github.gs618.easy.starter.transmission;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author gaosong
 */
@Order
public class RestTemplateKeyValueTransmitter implements ClientHttpRequestInterceptor {

    RestTemplateKeyValueTransmitter(List<RestTemplate> restTemplates) {
        if (Objects.nonNull(restTemplates)) {
            restTemplates.forEach(restTemplate -> {
                List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
                interceptors.add(interceptors.size(), RestTemplateKeyValueTransmitter.this);
            });
        }
    }

    @Override
    @NonNull
    public ClientHttpResponse intercept(
            @NonNull HttpRequest httpRequest, @NonNull byte[] bytes,
            @NonNull ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        KeyValues.transmit(httpRequest.getHeaders()::add);
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
