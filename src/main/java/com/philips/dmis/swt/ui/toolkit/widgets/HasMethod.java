package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasMethod extends HasStaticHTML {
    HasMethod getMethodImpl();

    String getMethod();

    void setMethod(String method);

    void setMethod(FormMethodType formMethod);

}
