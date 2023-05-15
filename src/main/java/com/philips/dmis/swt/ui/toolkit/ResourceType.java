package com.philips.dmis.swt.ui.toolkit;

public enum ResourceType {
    HTML("html"),
    JS("js"),

    ;

    final String name;

    ResourceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
