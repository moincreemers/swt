package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.JsType;

public interface HasValueType<V> {
    Widget asWidget();

    JsType getReturnType();
}