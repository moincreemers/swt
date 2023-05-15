package com.philips.dmis.swt.ui.toolkit.widgets;

public enum TypeType {
    CHECK("checkbox"),
    COLOR("color"),
    DATE("date"),
    DATETIME_LOCAL("datetime-local"),
    EMAIL("email"),
    FILE("file"),
    MONTH("month"),
    NUMBER("number"),
    PASSWORD("password"),
    RADIO("radio"),
    RANGE("range"),
    SEARCH("search"),
    TEL("tel"),
    TEXT("text"),
    TIME("time"),
    URL("url"),
    WEEK("week"),

    BUTTON("button"),
    SUBMIT("submit"),
    RESET("reset"),
    IMAGE("image"),

    ;

    final String value;

    TypeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
