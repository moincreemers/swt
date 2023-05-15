package com.philips.dmis.swt.ui.toolkit.widgets;

public interface IsFocusable extends HasStaticHTML {
    IsFocusable getFocusableImpl();

    boolean isFocusable();

    void setFocusable(boolean focusable);
}
