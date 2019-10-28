package com.github.gs618.easy.starter.transmission;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaosong
 */
@Slf4j
public class KeyValues {

    private final static Map<String, String> KEY_VALUES_MAP = new HashMap<>(10);

    public static void put(String key, String value) {
        KEY_VALUES_MAP.put(key, value);
    }

    public static void putAll(Map<String, String> map) {
        KEY_VALUES_MAP.putAll(map);
    }

    public static void remove(String key) {
        KEY_VALUES_MAP.remove(key);
    }

    static void transmit(KeyValueSetter keyValueSetter) {
        KEY_VALUES_MAP.forEach(keyValueSetter::set);
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

}
