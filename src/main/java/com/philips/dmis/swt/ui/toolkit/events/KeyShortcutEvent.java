package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

public class KeyShortcutEvent extends CustomEvent {
    public static final ValueStatement NAME = V.Const("name");

    public KeyShortcutEvent() {
        super(KeyShortcutEventHandler.EVENT_NAME);
    }
}
