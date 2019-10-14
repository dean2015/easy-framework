package com.github.gs618.easy.model.protocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author s.c.gao
 */
@Data
@Accessors(chain = true)
public class WebProtocol<T> {

    private static final Gson GSON = (new GsonBuilder()).serializeNulls().create();

    private Integer code;

    private T data;

    private Boolean success;

    private WebProtocol() {
    }

    public static <T> WebProtocol<T> newInstance() {
        return new WebProtocol<>();
    }

    @Override
    public String toString() {
        return GSON.toJson(this);
    }
}
