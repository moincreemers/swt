package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasText extends HasStaticHTML {
    String CSS_CLASS_TEXT = "text";

    static String getTextId(String id) {
        return id + "_text";
    }

    HasText getTextImpl();

    TextFormatType getTextFormat();

    void setTextFormat(TextFormatType textFormatType);

    String getText();

    void setText(String text);

    boolean isText();
}
