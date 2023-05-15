package com.philips.dmis.swt.ui.toolkit.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A map that allows writing a distinct key once.
 */
public class ProtectedHashMap<K, V> extends LinkedHashMap<K, V> {
    @Override
    public V put(K key, V value) {
        if (containsKey(key)) {
            throw new IllegalArgumentException("protected key " + key);
        }
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (K key : m.keySet()) {
            if (containsKey(key)) {
                throw new IllegalArgumentException("protected key " + key);
            }
        }
        super.putAll(m);
    }
}
