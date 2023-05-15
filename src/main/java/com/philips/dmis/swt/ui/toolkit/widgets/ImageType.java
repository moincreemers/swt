package com.philips.dmis.swt.ui.toolkit.widgets;

public enum ImageType {
    DEFAULT("", "d"),

    ;
    final String className;
    final  String shortName;

    ImageType(String className, String shortName) {
        this.className = className;
        this.shortName = shortName;
    }

    public String getClassName() {
        return className;
    }

    public String getShortName() {
        return shortName;
    }
}
