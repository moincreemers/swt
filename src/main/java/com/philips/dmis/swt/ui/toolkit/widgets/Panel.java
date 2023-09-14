package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.css.CssConstant;
import com.philips.dmis.swt.ui.toolkit.css.CssLength;
import com.philips.dmis.swt.ui.toolkit.css.CssValue;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement({"panelType", "overflowType", "width", "height"})
public class Panel extends ContainerWidget<Panel> {
    private PanelType panelType = PanelType.DEFAULT;
    private OverflowType overflowType = OverflowType.FIT_CONTENT;
    private CssValue width = CssConstant.EMPTY;
    private CssValue height = CssConstant.EMPTY;

    protected Panel(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.PANEL);
    }

    protected Panel(WidgetConfigurator widgetConfigurator, WidgetType widgetType) {
        super(widgetConfigurator, widgetType);
    }

    protected Panel(WidgetType widgetType) {
        super(widgetType);
    }

    protected Panel(String name, WidgetType widgetType) {
        super(name, widgetType);
    }

    protected Panel(String name, WidgetType widgetType, PanelType panelType) {
        super(name, widgetType);
        setPanelType(panelType);
    }

    protected Panel(WidgetType widgetType, PanelType panelType, Widget... widgets) throws WidgetConfigurationException {
        this(NAMELESS, widgetType, panelType, widgets);
    }

    public Panel() {
        super(WidgetType.PANEL);
    }

    public Panel(String name) {
        super(name, WidgetType.PANEL);
    }

    public Panel(PanelType panelType) {
        this(NAMELESS, WidgetType.PANEL, panelType);
    }

    public Panel(WidgetType widgetType, PanelType panelType) {
        this(NAMELESS, widgetType, panelType);
    }

    public Panel(Widget... widgets) throws WidgetConfigurationException {
        this(WidgetType.PANEL, PanelType.DEFAULT, widgets);
    }

    public Panel(String name, Widget... widgets) throws WidgetConfigurationException {
        this(name, WidgetType.PANEL, PanelType.DEFAULT, widgets);
    }

    public Panel(PanelType panelType, Widget... widgets) throws WidgetConfigurationException {
        this(WidgetType.PANEL, panelType, widgets);
    }

    protected Panel(String name, WidgetType widgetType, PanelType panelType, Widget... widgets) throws WidgetConfigurationException {
        super(name, widgetType);
        setPanelType(panelType);
        addAll(widgets);
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        super.validate(toolkit);
        if (overflowType.requiresSize) {
            // todo:
        }
    }

    @Override
    protected boolean acceptWidget(Widget widget) {
        return true;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        if (overflowType.requiresSize) {
            StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
            style.add("width", width.toString());
            style.add("height", height.toString());
        }
    }

    public PanelType getPanelType() {
        return panelType;
    }

    public Panel setPanelType(PanelType panelType) {
        if (panelType == null) {
            return this;
        }
        if (this.panelType != PanelType.DEFAULT) {
            removeClassName(this.panelType.className);
        }
        this.panelType = panelType;
        addClassName(panelType.className);
        return this;
    }

    public OverflowType getOverflowType() {
        return overflowType;
    }

    public Panel setOverflowType(OverflowType overflowType) {
        if (overflowType == null) {
            return this;
        }
        if (this.overflowType != OverflowType.DEFAULT) {
            removeClassName(this.overflowType.className);
        }
        this.overflowType = overflowType;
        addClassName(this.overflowType.className);
        return this;
    }

    public Panel setOverflowAndSize(OverflowType overflowType, CssValue width, CssValue height) {
        setOverflowType(overflowType);
        setWidth(width);
        setHeight(height);
        return this;
    }

    public CssValue getWidth() {
        return width;
    }

    public void setWidth(CssValue width) {
        if (width == null) {
            width = new CssLength();
        }
        this.width = width;
    }

    public CssValue getHeight() {
        return height;
    }

    public void setHeight(CssValue height) {
        if (height == null) {
            height = new CssLength();
        }
        this.height = height;
    }
}
