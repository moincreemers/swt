package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasSrc<T extends Widget> extends HasStaticHTML {
    public static final String DEFAULT_VALUE_SRC = "";

    HasSrc<T> getSrcImpl();

    String getSrc();

    T setSrc(String src);
}
