package com.philips.dmis.swt.ui.toolkit.widgets;

import java.util.List;

public interface HasURL extends HasAbstractURL {
    Widget asWidget();

    String getURL();

    void setURL(String url);

    void addParameter(String name, String defaultValue);

    List<Parameter> getParameters();
}
