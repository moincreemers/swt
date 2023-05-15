package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasMax extends HasStaticHTML {
    HasMax getMaxImpl();

    Float getMax();

    void setMax(Float max);

    Float getValue();

    void setValue(Float value);
}
