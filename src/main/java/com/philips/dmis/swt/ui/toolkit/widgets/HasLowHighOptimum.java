package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasLowHighOptimum extends HasStaticHTML {
    HasLowHighOptimum getLowHighOptimumImpl();

    Integer getLow();

    void setLow(Integer low);

    Integer getHigh();

    void setHigh(Integer high);

    Integer getOptimum();

    void setOptimum(Integer optimum);
}
