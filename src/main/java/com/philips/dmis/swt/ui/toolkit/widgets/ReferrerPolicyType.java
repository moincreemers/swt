package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum ReferrerPolicyType implements HasDefault<ReferrerPolicyType> {
    /**
     * The Referer header will not be sent.
     */
    NO_REFERRER("no-referrer"),
    /**
     * The Referer header will not be sent to origins without TLS (HTTPS).
     */
    NO_REFERRER_WHEN_DOWNGRADE("no-referrer-when-downgrade"),
    /**
     * The sent referrer will be limited to the origin of the referring page: its scheme, host, and port.
     */
    ORIGIN("origin"),
    /**
     * The referrer sent to other origins will be limited to the scheme, the host, and the port. Navigations on the same origin will still include the path.
     */
    ORIGIN_WHEN_CROSS_ORIGIN("origin-when-cross-origin"),
    /**
     * A referrer will be sent for same origin, but cross-origin requests will contain no referrer information.
     */
    SAME_ORIGIN("same-origin"),
    /**
     * Only send the origin of the document as the referrer when the protocol security level stays the same (HTTPS→HTTPS), but don't send it to a less secure destination (HTTPS→HTTP).
     */
    STRICT_ORIGIN("strict-origin"),
    /**
     * Send a full URL when performing a same-origin request, only send the origin when the protocol security level stays the same (HTTPS→HTTPS), and send no header to a less secure destination (HTTPS→HTTP).
     * Note: Default
     */
    STRICT_ORIGIN_WHEN_CROSS_ORIGIN("strict-origin-when-cross-origin"),
    /**
     * The referrer will include the origin and the path (but not the fragment, password, or username). This value is unsafe, because it leaks origins and paths from TLS-protected resources to insecure origins.
     */
    UNSAFE_URL("unsafe-url"),

    ;

    final String value;

    ReferrerPolicyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public ReferrerPolicyType getDefault() {
        return STRICT_ORIGIN_WHEN_CROSS_ORIGIN;
    }
}
