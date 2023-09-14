package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum ImageType implements HasDefault<ImageType> {
    DEFAULT(""),
    ;
    final String className;

    ImageType(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public ImageType getDefault() {
        return DEFAULT;
    }
}
