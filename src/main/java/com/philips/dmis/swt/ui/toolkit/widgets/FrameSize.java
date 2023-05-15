package com.philips.dmis.swt.ui.toolkit.widgets;

public class FrameSize {
    public static final FrameSize FIT_CONTAINER = new FrameSize("", "", "");

    public static FrameSize customSize(String width, String height) {
        return new FrameSize("tk-frame-custom", width, height);
    }

    final String className, width, height;

    public FrameSize(String className, String width, String height) {
        this.className = className;
        this.width = width;
        this.height = height;
    }
}
