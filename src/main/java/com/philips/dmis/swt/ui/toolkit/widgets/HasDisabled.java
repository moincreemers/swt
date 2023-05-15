package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasDisabled extends HasStaticHTML {
    HasDisabled getDisabledImpl();

    boolean getDisabled();

    void setDisabled(boolean disabled);
}
