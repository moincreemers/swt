package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

public class ResponseEvent extends CustomEvent {
    public static final ValueStatement HTTP_STATUS = V.Const("status");
    public static final ValueStatement HTTP_RESPONSE_URL = V.Const("url");
    public static final ValueStatement HTTP_CONTENT_TYPE = V.Const("contentType");
    public static final ValueStatement HTTP_RESPONSE_DATA = V.Const("data");

    public static ValueStatement isOk() {
        return V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_OK());
    }

    public static ValueStatement isUnauthorized() {
        return V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_UNAUTHORIZED());
    }

    public static ValueStatement isForbidden() {
        return V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_FORBIDDEN());
    }

    public static ValueStatement isServerError() {
        return V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_SERVER_ERROR());
    }

    public static ValueStatement isBadRequest() {
        return V.Is(V.GetEvent(ResponseEvent.HTTP_STATUS), V.HTTP_BAD_REQUEST());
    }

    public static ValueStatement getResponseData() {
        return V.ParseJSON(V.GetEvent(HTTP_RESPONSE_DATA));
    }

    public static ValueStatement getResponseText() {
        return V.GetEvent(HTTP_RESPONSE_DATA);
    }

    public ResponseEvent() {
        super(ResponseEventHandler.EVENT_NAME);
    }
}
