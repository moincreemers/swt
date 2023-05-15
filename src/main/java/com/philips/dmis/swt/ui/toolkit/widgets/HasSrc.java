package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasSrc<T extends Widget> extends HasStaticHTML {
    HasSrc<T> getSrcImpl();

    String getSrc();

    T setSrc(String src);
}
