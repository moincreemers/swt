package com.philips.dmis.swt.ui.toolkit.widgets;

public class Size {
    public static final String DEFAULT_WIDTH = "300px";
    public static final String DEFAULT_HEIGHT = "150px";
    public static final String AUTO = "auto";

    public static final String DEFAULT_TABPAGE_WIDTH = "";
    public static final String DEFAULT_TABPAGE_HEIGHT = "300px";

    public static Size getDefault() {
        return new Size();
    }

    public static Size getDefaultTabPage() {
        return new Size(DEFAULT_TABPAGE_WIDTH, DEFAULT_TABPAGE_HEIGHT);
    }

    String width, height;

    public Size() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Size(String width, String height) {
        if (width == null) {
            width = DEFAULT_WIDTH;
        }
        if (height == null) {
            height = DEFAULT_HEIGHT;
        }
        this.width = width;
        this.height = height;
    }

    public void validate() throws WidgetConfigurationException {
    }

    @Override
    public String toString() {
        return "width:" + width + ";height:" + height + ";";
    }
}
