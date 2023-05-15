package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasName<T extends Widget> extends HasStaticHTML {
    Widget asWidget();

    HasName<T> getNameImpl();

    String getName();

    T setName(String name);
}
