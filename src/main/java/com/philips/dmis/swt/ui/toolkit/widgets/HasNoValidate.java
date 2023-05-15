package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasNoValidate extends HasStaticHTML {
    HasNoValidate getNoValidateImpl();

    Boolean getNoValidate();

    void setNoValidate(Boolean noValidate);
}
