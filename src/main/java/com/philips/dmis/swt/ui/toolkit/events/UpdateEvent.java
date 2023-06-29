package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

public class UpdateEvent extends CustomEvent {
    public static ValueStatement Items() {
        return V.GetEvent(V.Const(".object.data.items"));
    }

    public UpdateEvent() {
        super(UpdateEventHandler.EVENT_NAME);
    }
}
