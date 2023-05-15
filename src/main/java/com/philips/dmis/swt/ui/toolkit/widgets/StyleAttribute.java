package com.philips.dmis.swt.ui.toolkit.widgets;

import java.util.ArrayList;
import java.util.Map;

public class StyleAttribute {
    final Map<String, String> attributes;
    final String name;

    public StyleAttribute(Map<String, String> attributes, String name) {
        this.attributes = attributes;
        this.name = name;
    }

    public void add(String property, String value) {
        String val = attributes.get(name);
        java.util.List<String> values = new ArrayList<>();
        if (val != null) {
            String[] x = val.split(";");
            for (String y : x) {
                if (y != null && !y.isEmpty()) {
                    values.add(y);
                }
            }
        }
        String newValue = property + ":" + value;
        if (!values.contains(newValue)) {
            values.add(newValue);
        }
        attributes.put(name, String.join(";", values));
    }
}
