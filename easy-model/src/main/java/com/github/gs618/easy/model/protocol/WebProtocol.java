package com.github.gs618.easy.model.protocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author s.c.gao
 */
@Data
@Accessors(chain = true)
public class WebProtocol<T extends Serializable> {

    private static final Gson GSON = (new GsonBuilder()).serializeNulls().create();

    private Integer code;

    private T data;

    private Boolean success;

    private WebProtocol() {
    }

    public static <T extends Serializable> WebProtocol<T> newInstance() {
        return new WebProtocol<>();
    }

    @Override
    public String toString() {
        return GSON.toJson(this);
    }
}
