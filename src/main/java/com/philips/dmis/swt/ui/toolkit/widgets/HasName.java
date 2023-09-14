package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasName<T extends Widget> extends HasStaticHTML {
    public static final String NAMELESS = "";

    Widget asWidget();

    HasName<T> getNameImpl();

    String getName();

    T setName(String name);
}
