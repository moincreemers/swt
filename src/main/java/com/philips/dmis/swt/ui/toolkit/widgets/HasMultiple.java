package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasMultiple extends HasStaticHTML {
    HasMultiple getMultipleImpl();

    boolean getMultiple();

    void setMultiple(boolean multiple);
}
