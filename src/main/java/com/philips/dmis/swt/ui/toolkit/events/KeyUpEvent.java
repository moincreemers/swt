package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

public class KeyUpEvent extends KeyEvent {
    public static final ValueStatement KEY = V.Const("key");

    public KeyUpEvent() {
        super(KeyUpEventHandler.EVENT_NAME);
    }
}
