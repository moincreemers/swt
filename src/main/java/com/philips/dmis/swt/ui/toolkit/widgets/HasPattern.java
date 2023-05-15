package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasPattern extends HasStaticHTML {
    HasPattern getPatternImpl();

    String getPattern();

    void setPattern(String pattern);
}
