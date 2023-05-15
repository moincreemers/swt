package com.philips.dmis.swt.ui.toolkit.dto;

import java.io.Serializable;

public class ValueWrapper implements Serializable {
    private Object value;

    public ValueWrapper(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
