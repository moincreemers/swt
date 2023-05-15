package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasNumberedText {
    String CSS_CLASS_NUMBERED = "numbered";
    String CSS_CLASS_NUMBER_OUTER = "number";
    String CSS_CLASS_NUMBER_TEXT = "number-text";

    static String getNumberTextId(String id) {
        return id + "_number";
    }

    int getLevel();

    void setLevel(int level);

    boolean isNumberingEnabled();

    void setNumberingEnabled(boolean numbered);
}
