package com.philips.dmis.swt.ui.toolkit.dto;

public class Field {
    public static final String TYPE_TEXT = "text";
    public static final String TYPE_CHECKBOX = "c";
    public static final String TYPE_RADIOBUTTON = "r";
    public static final String TYPE_LINK = "a";
    public static final String TYPE_BUTTON = "b";

    private String type;
    private String source;
    private String text;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
