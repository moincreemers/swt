package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.KeyShortcutEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.HashMap;
import java.util.Map;

public class KeyShortcutWidget extends Widget {
    private final Map<String, KeyShortcut[]> items = new HashMap<>();

    public KeyShortcutWidget() {
        super(WidgetType.KEYSHORTCUT);
    }

    public KeyShortcutWidget addShortcut(String name, KeyShortcut... keyShortcuts) {
        items.put(name, keyShortcuts);
        return this;
    }

    public KeyShortcutWidget onKeyShortcut(KeyShortcutEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
        style.add("display", "none");
    }
}
