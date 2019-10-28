package com.github.gs618.easy.starter.transmission;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gaosong
 */
@Slf4j
public class KeyValues {

    private final TransmissionProperties properties;

    KeyValues(TransmissionProperties properties) {
        this.properties = properties;
    }

    void transmit(KeyValueSetter keyValueSetter) {
        properties.getTransmission().forEach(keyValueSetter::set);
    }

    /**
     * 键值对设置器
     */
    public interface KeyValueSetter {
        /**
         * 设置键值对
         *
         * @param key   key
         * @param value value
         */
        void set(String key, String value);
    }

    /**
     * Tracing信息获取器
     */
    public interface TracingGetter {
        /**
         * 获取tracing属性
         *
         * @param key key
         * @return tracing value
         */
        String get(String key);
    }
}
