package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;

public final class TokenUtil {
    private static final String HTTP_HEADER_AUTHORIZATION = "Authorization";
    public static final String GLOBAL_ACCESS_TOKEN_KEY = "access-token";

    private TokenUtil() {
    }

    public static void setAuthorizationHeader(HasURL hasURL) throws WidgetConfigurationException {
        hasURL.setHttpHeader(HTTP_HEADER_AUTHORIZATION, V.GetAuthorizationHeaderBearer());
    }
}
