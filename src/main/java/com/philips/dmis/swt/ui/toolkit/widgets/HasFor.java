package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasFor extends HasStaticHTML {
    HasFor getForImpl();

    String getFor();

    void setFor(String _for);

    void setFor(Widget widget);
}
