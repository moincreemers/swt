package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;

public interface HasValue<T extends Widget> extends HasName<T>, HasStaticHTML {
    Widget asWidget();

    HasValue<T> getValueImpl();

    String getValue();

    T setValue(String value);

    T onChange(ChangeEventHandler eventHandler);
}
