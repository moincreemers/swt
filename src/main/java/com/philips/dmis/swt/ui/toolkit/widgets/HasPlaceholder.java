package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasPlaceholder extends HasStaticHTML {
    HasPlaceholder getPlaceholderImpl();

    String getPlaceholder();

    void setPlaceholder(String placeholder);
}
