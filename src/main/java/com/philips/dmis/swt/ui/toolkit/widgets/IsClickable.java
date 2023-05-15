package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;

public interface IsClickable<T extends Widget> extends HasStaticHTML {
    IsClickable getClickableImpl();

    T onClick(ClickEventHandler eventHandler);
}
