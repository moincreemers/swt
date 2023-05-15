package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasType extends HasStaticHTML {
    HasType getTypeImpl();

    TypeType getType();

    void setType(TypeType type);
}
