package com.philips.dmis.swt.ui.toolkit.widgets;

public enum MimeType {
    URL_ENCODED("application/x-www-form-urlencoded"),
    MULTIPART("multipart/form-data"),

    ;

    final String value;

    MimeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

