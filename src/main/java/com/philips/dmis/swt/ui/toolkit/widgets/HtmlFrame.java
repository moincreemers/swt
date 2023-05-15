package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.InitEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.Map;

public class HtmlFrame extends ValueWidget<HtmlFrame> {
    private FrameSize frameSize = FrameSize.FIT_CONTAINER;

    public HtmlFrame() {
        this("", FrameSize.FIT_CONTAINER);
    }

    public HtmlFrame(String name) {
        this(name, FrameSize.FIT_CONTAINER);
    }

    public HtmlFrame(FrameSize frameSize) {
        this("", frameSize);
    }

    public HtmlFrame(String name, FrameSize frameSize) {
        super(name, WidgetType.FRAME);
        setFrameSize(frameSize);
    }

    public HtmlFrame addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(DataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
        return this;
    }

    public HtmlFrame onInit(InitEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public FrameSize getFrameSize() {
        return frameSize;
    }

    public HtmlFrame setFrameSize(FrameSize frameSize) {
        if (frameSize == null) {
            return this;
        }
        if (!this.frameSize.className.isEmpty()) {
            removeClassName(this.frameSize.className);
        }
        this.frameSize = frameSize;
        addClassName(frameSize.className);
        return this;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        if (!frameSize.width.isEmpty()) {
            StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
            style.add("width", frameSize.width);
        }
        if (!frameSize.height.isEmpty()) {
            StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
            style.add("height", frameSize.height);
        }
    }
}
