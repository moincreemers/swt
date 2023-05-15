package com.philips.dmis.swt.ui.toolkit;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

public class DefaultCacheStrategyImpl implements CacheStrategy {
    static final String SWT_KEY = "swt-cache";
    static final String LAST_COMPILE_TIME = "swt-last-compile-time";
    static final String ETAG = "swt-etag";
    static final String DEFAULT_ETAG = "";
    static final int MAX_AGE = 10; //30 * (24 * 60 * 60); // 30 days in seconds
    final HttpServletRequest request;
    final long now = System.currentTimeMillis();

    public DefaultCacheStrategyImpl(HttpServletRequest request) {
        this.request = request;
    }

    private Map getData() {
        HttpSession session = request.getSession();
        Object value = session.getAttribute(SWT_KEY);
        Map map = new HashMap<>();
        if (value instanceof Map) {
            map = (Map) value;
        }
        session.setAttribute(SWT_KEY, map);
        return map;
    }

    private <T> T getValue(String key, T defaultValue) {
        Object value = getData().get(key);
        if (value == null) {
            return defaultValue;
        }
        return (T) value;
    }

    private void setValue(String key, Object value) {
        getData().put(key, value);
    }

    //


    @Override
    public long getNowMillis() {
        return now;
    }

    @Override
    public int getMaxAgeSeconds() {
        return MAX_AGE;
    }

    @Override
    public void setETag(String eTag) {
        setValue(ETAG, eTag);
        setValue(LAST_COMPILE_TIME, now);
    }

    @Override
    public String getETag() {
        return getValue(ETAG, DEFAULT_ETAG);
    }

    @Override
    public boolean exists(HttpServletRequest request) {
        String eTag = request.getHeader("If-None-Match");
        if (eTag == null || eTag.isEmpty()) {
            return false;
        }
        return eTag.equals(getETag());
    }
}
