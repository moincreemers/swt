package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

public class ErrorEvent extends CustomEvent {
    public static final ValueStatement HTTP_STATUS = V.Const("status");
    public ErrorEvent() {
        super(ErrorEventHandler.EVENT_NAME);
    }
}
