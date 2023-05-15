package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasRange<T> extends HasStaticHTML {
    HasRange getRangeImpl();

    T getRangeMin();

    void setRangeMin(T rangeMin);

    T getRangeMax();

    void setRangeMax(T rangeMax);
}
