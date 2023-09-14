package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;

public interface HasValue<T extends Widget, V> extends HasName<T>, HasStaticHTML, HasValueType<V> {
    String DEFAULT_VALUE_STRING = "";
    Boolean DEFAULT_VALUE_BOOLEAN = Boolean.FALSE;
    Double DEFAULT_VALUE_NUMBER = 0d;
    Object DEFAULT_VALUE_OBJECT = null;

    Widget asWidget();

    HasValue<T, V> getValueImpl();

    V getValue();

    T setValue(V value);

    T onChange(ChangeEventHandler eventHandler);
}
