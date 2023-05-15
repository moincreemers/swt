package com.philips.dmis.swt.ui.toolkit.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArrayWrapper implements Serializable {
    private List<Object> items = new ArrayList<>();

    public ArrayWrapper(Object[] values) {
        for (Object value : values) {
            items.add(value);
        }
    }

    public ArrayWrapper(Collection<Object> values) {
        items.addAll(values);
    }

    public List<Object> getItems() {
        return items;
    }
}
