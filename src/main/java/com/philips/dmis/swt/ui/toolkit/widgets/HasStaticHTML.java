package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public interface HasStaticHTML {
    Widget asWidget();

    void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html);

    void getHtmlAttributes(Map<String, String> htmlAttributes);

    void validate(Toolkit toolkit) throws WidgetConfigurationException;
}
