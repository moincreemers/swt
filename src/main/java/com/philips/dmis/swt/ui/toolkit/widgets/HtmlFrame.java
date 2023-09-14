package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.InitEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement("frameSize")
public class HtmlFrame extends ValueWidget<HtmlFrame, String, ValueDataSourceUsage> {
    private FrameSize frameSize = FrameSize.FIT_CONTAINER;

    public HtmlFrame(WidgetConfigurator widgetConfigurator, String name) {
        super(widgetConfigurator, name, WidgetType.FRAME, JsType.STRING);
    }

    public HtmlFrame() {
        this(NAMELESS, FrameSize.FIT_CONTAINER);
    }

    public HtmlFrame(String name) {
        this(name, FrameSize.FIT_CONTAINER);
    }

    public HtmlFrame(FrameSize frameSize) {
        this(NAMELESS, frameSize);
    }

    public HtmlFrame(String name, FrameSize frameSize) {
        super(name, WidgetType.FRAME, JsType.STRING);
        setFrameSize(frameSize);
    }

    public HtmlFrame addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ValueDataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
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
