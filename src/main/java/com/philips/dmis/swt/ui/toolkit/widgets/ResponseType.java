package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum ResponseType implements HasDefault<ResponseType> {
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

    @Override
    public ResponseType getDefault() {
        return ResponseType.DEFAULT;
    }
}
