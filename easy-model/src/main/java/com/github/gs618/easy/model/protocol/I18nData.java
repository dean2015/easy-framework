package com.github.gs618.easy.model.protocol;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author s.c.gao
 */
@Data
@Accessors(chain = true)
public class I18nData implements Serializable {

    private String message;

    private String[] parameters;

    private I18nData() {
    }

    public static I18nData newInstance() {
        return new I18nData();
    }

}
