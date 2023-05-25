package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

public final class HttpHeaderUtil {
    private static final String HTTP_HEADER_AUTHORIZATION = "Authorization";
    public static final String GLOBAL_ACCESS_TOKEN_KEY = "access-token";

    private static final String HTTP_HEADER_CACHE_CONTROL = "Cache-Control";
    private static final ValueStatement CACHE_CONTROL_NO_CACHE = V.Const("no-cache, no-store, max-age=0");

    private static final String HTTP_HEADER_EXPIRES = "Expires";
    private static final ValueStatement EXPIRED = V.Const("Thu, 1 Jan 1970 00:00:00 GMT");

    private static final String HTTP_HEADER_PRAGMA = "Pragma";
    private static final ValueStatement PRAGMA_NO_CACHE = V.Const("no-cache");


    private HttpHeaderUtil() {
    }

    public static <T extends HasAbstractURL> T setAuthorizationHeader(T hasAbstractURL) throws WidgetConfigurationException {
        hasAbstractURL.setHttpHeader(HTTP_HEADER_AUTHORIZATION, V.GetAuthorizationHeaderBearer());
        return hasAbstractURL;
    }

    public static <T extends HasAbstractURL> T setNoCache(T hasAbstractURL) throws WidgetConfigurationException{
        hasAbstractURL.setHttpHeader(HTTP_HEADER_CACHE_CONTROL, CACHE_CONTROL_NO_CACHE);
        hasAbstractURL.setHttpHeader(HTTP_HEADER_EXPIRES, EXPIRED);
        hasAbstractURL.setHttpHeader(HTTP_HEADER_PRAGMA, PRAGMA_NO_CACHE);
        return hasAbstractURL;
    }
}
