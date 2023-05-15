package com.philips.dmis.swt.ui.toolkit.dto;

import java.io.Serializable;

public class ContentType implements Serializable {
    private String value;

    public ContentType() {
        // for serializable
    }

    public ContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
