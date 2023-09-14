package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasIcon extends HasStaticHTML {
    public static final String DEFAULT_VALUE_ICON = "";
    static String getIconId(String id) {
        return id + "_icon";
    }

    HasIcon getIconImpl();

    IconsWidget getIconsWidget();

    void setIconsWidget(IconsWidget iconsWidget);

    String getIcon();

    void setIcon(String icon);

    boolean isIcon();
}
