package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasLength extends HasStaticHTML {
    HasLength getLengthImpl();

    Integer getLengthMin();

    void setLengthMin(Integer lengthMin);

    Integer getLengthMax();

    void setLengthMax(Integer lengthMax);
}
