package com.philips.dmis.swt.ui.toolkit.widgets;

public class ImageSize {
    public static final ImageSize DEFAULT = new ImageSize("", "", "");

    public static ImageSize customSize(String width, String height) {
        return new ImageSize("tk-image-custom", width, height);
    }

    final String className, width, height;

    public ImageSize(String className, String width, String height) {
        this.className = className;
        this.width = width;
        this.height = height;
    }
}
