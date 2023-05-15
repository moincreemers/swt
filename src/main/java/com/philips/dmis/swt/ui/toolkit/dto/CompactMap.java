package com.philips.dmis.swt.ui.toolkit.dto;

import java.util.HashMap;

public class CompactMap extends HashMap<String, Object> {
    @Override
    public Object put(String key, Object value) {
        if (key == null || key.isEmpty() || value == null) {
            return null;
        }
        if (value instanceof HasValue) {
            value = ((HasValue) value).getValue();
        }
        return super.put(key, value);
    }
}
