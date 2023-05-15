package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasAction extends HasStaticHTML {
    HasAction getActionImpl();

    String getAction();

    void setAction(String action);
}
