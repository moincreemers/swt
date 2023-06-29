package com.philips.dmis.swt.ui.toolkit.widgets;

public enum ImageType {
    DEFAULT(""),
    ;
    final String className;

    ImageType(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
