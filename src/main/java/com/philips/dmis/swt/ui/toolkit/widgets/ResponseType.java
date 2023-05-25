package com.philips.dmis.swt.ui.toolkit.widgets;

public enum ResponseType {
    DEFAULT(""),
    TEXT("text"),
    BLOB("blob"),
    ARRAYBUFFER("arraybuffer"),
    DOCUMENT("document"),
    JSON("json"),

    ;

    private String value;

    ResponseType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
