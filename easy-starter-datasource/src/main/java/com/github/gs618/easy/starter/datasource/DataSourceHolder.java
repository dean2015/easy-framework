package com.github.gs618.easy.starter.datasource;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 保存当前datasource在RoutingDataSource中key
 * 使用ThreadLocal，每个线程的操作不会影响其他线程的datasource
 *
 * @author gaosong
 */
@Slf4j
class DataSourceHolder {

    private static final ThreadLocal<String> CURRENT_DATASOURCE_KEY = new InheritableThreadLocal<String>();

    static void set(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        CURRENT_DATASOURCE_KEY.set(key);
    }

    /**
     * 如果为null 默认指向DEFAULT
     */
    static String get() {
        return CURRENT_DATASOURCE_KEY.get() == null
                ? DataSourceProperties.DEFAULT
                : CURRENT_DATASOURCE_KEY.get();
    }

    /**
     *
     */
    static void remove() {
        CURRENT_DATASOURCE_KEY.remove();
    }
}
