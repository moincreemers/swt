package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

public class PageVariableChangeEvent extends CustomEvent {
    public static ValueStatement Name(){
        return V.GetEvent(V.Const("name"));
    }
    public static ValueStatement Value(){
        return V.GetEvent(V.Const("value"));
    }

    public PageVariableChangeEvent() {
        super(PageVariableChangeEventHandler.EVENT_NAME);
    }
}
