package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

public class OpenEvent extends CustomEvent {
    public static final ValueStatement VALUE = V.Const("value");
    public static final ValueStatement RECORD = V.Const("record");
    private Object value;

    public OpenEvent() {
        super(OpenEventHandler.EVENT_NAME);
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
