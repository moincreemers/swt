package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public class DownloadImpl implements HasDownload {
    private final Widget widget;
    private String download;

    public DownloadImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasDownload getDownloadImpl() {
        return this;
    }

    @Override
    public String getDownload() {
        return download;
    }

    @Override
    public void setDownload(String download) {
        this.download = download;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (download != null) {
            htmlAttributes.put("download", download);
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
