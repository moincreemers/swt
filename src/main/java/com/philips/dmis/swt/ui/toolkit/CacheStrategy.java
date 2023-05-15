package com.philips.dmis.swt.ui.toolkit;

import jakarta.servlet.http.HttpServletRequest;

// todo: we need a JAR/WAR version number to detect the JS Client has changed
public interface CacheStrategy {
    long getNowMillis();

    int getMaxAgeSeconds();

    void setETag(String eTag);

    String getETag();

    boolean exists(HttpServletRequest request);
}
