package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public class IconImpl implements HasIcon {
    private final Widget widget;
    private IconsWidget iconsWidget;
    private String icon;

    public IconImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasIcon getIconImpl() {
        return this;
    }

    @Override
    public IconsWidget getIconsWidget() {
        return iconsWidget;
    }

    @Override
    public void setIconsWidget(IconsWidget iconsWidget) {
        this.iconsWidget = iconsWidget;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean isIcon() {
        return iconsWidget != null && icon != null && !icon.isEmpty();
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        htmlAttributes.put("tk-has-icon", isIcon() ? "true" : "false");
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {
        html.append(String.format("<span id=\"%s\" class=\"%s\">%s</span>",
                HasIcon.getIconId(widget.getId()),
                isIcon() ? iconsWidget.getClassName() : "", isIcon() ? icon : ""));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (isIcon()) {
            iconsWidget.validateCodepointExists(icon);
        }
    }
}
