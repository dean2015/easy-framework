package com.github.gs618.easy.starter.transmission;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author gaosong
 */
@Slf4j
public class KeyValues {

    private final static TransmittableThreadLocal<Map<String, String>> KEY_VALUES_MAP = new TransmittableThreadLocal<>();

    private final static int MAP_SIZE = 10;

    static {
        KEY_VALUES_MAP.set(new HashMap<>(MAP_SIZE));
    }

    private static synchronized Map<String, String> map() {
        Map<String, String> map = KEY_VALUES_MAP.get();
        if (Objects.isNull(map)) {
            map = new HashMap<>(MAP_SIZE);
            KEY_VALUES_MAP.set(map);
        }
        return map;
    }

    public static String put(String key, String value) {
        return map().put(key, value);
    }

    public static void putAll(Map<String, String> map) {
        map().putAll(map);
    }

    public static String get(String key) {
        return map().get(key);
    }

    public static String remove(String key) {
        return map().remove(key);
    }

    public static void clear() {
        KEY_VALUES_MAP.remove();
    }

    static void transmit(KeyValueSetter keyValueSetter) {
        map().forEach(keyValueSetter::set);
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
