package com.philips.dmis.swt.ui.toolkit.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * A map that contains distinct keys and values.
 */
public class DistinctMap extends HashMap<String, String> {
    private final Map<String, String> reverseIndex = new HashMap<>();

    @Override
    public String put(String key, String value) {
        if (keySet().contains(key)) {
            throw new IllegalArgumentException("duplicate key:" + key);
        }
        if (reverseIndex.containsKey(value)) {
            return reverseIndex.get(value);
        }
        super.put(key, value);
        reverseIndex.put(value, key);
        return key;
    }
}
