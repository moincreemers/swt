package com.philips.dmis.swt.ui.toolkit.widgets;

public enum ContentType {
    NONE(""),
    TEXT("text/plain"),
    JSON("application/json"),
    XML("text/xml"),
    FORM_URLENCODED("application/x-www-form-urlencoded"),
    FORM_MULTIPART("multipart/form-data"),
    OCTET_STREAM("application/octet-stream"),

    ;

    private String encoding;

    ContentType(String encoding) {
        this.encoding = encoding;
    }

    public String getEncoding() {
        return encoding;
    }
}
