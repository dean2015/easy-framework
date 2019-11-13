package com.github.gs618.easy.starter.datasource;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.EmptyStackException;
import java.util.Objects;
import java.util.Stack;

/**
 * 保存当前datasource在RoutingDataSource中key
 * 使用ThreadLocal，每个线程的操作不会影响其他线程的datasource
 *
 * @author gaosong
 */
@Slf4j
class DataSourceHolder {

    private static final ThreadLocal<Stack<String>> CURRENT_DATASOURCE_KEY = new InheritableThreadLocal<>();

    public static void set(String key) {
        Stack<String> stack = CURRENT_DATASOURCE_KEY.get();
        if (Objects.isNull(CURRENT_DATASOURCE_KEY.get())) {
            stack = new Stack<>();
            CURRENT_DATASOURCE_KEY.set(stack);
        }
        stack.push(key);
    }

    /**
     * 如果为null 默认指向DEFAULT
     */
    public static String get() {
        Stack<String> stack = CURRENT_DATASOURCE_KEY.get();
        if(Objects.isNull(stack)){
            return DataSourceProperties.DEFAULT;
        }
        try {
            String key = stack.peek();
            return StringUtils.isBlank(key)
                    ? DataSourceProperties.DEFAULT
                    : key;
        } catch (EmptyStackException e) {
            return DataSourceProperties.DEFAULT;
        }
    }

    /**
     *
     */
    public static void remove() {
        Stack<String> stack = CURRENT_DATASOURCE_KEY.get();
        if (stack.isEmpty()) {
            CURRENT_DATASOURCE_KEY.remove();
        } else {
            stack.pop();
        }
    }
}
