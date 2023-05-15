package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

public class ResponseEvent extends CustomEvent {
    public static final ValueStatement HTTP_STATUS = V.Const("status");
    public static final ValueStatement HTTP_RESPONSE_URL = V.Const("url");
    public static final ValueStatement HTTP_CONTENT_TYPE = V.Const("contentType");
    public static final ValueStatement HTTP_RESPONSE_TEXT = V.Const("responseText");

    public ResponseEvent() {
        super(ResponseEventHandler.EVENT_NAME);
    }
}
