package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasReadonly extends HasStaticHTML {
    HasReadonly getReadonlyImpl();

    boolean getReadonly();

    void setReadonly(boolean readonly);
}
