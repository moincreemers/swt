package com.philips.dmis.swt.ui.toolkit.widgets;

public class Parameter {
    private String name;
    private String defaultValue;

    public Parameter(String name, String defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
