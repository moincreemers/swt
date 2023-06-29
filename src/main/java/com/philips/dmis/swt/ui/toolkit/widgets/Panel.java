package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.Map;

public class Panel extends ContainerWidget<Panel> {
    private PanelType panelType = PanelType.DEFAULT;
    private Overflow overflow = Overflow.FIT_CONTENT;
    private Size size = new Size();

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
        this("", widgetType, panelType, widgets);
    }

    public Panel() {
        super(WidgetType.PANEL);
    }

    public Panel(String name) {
        super(name, WidgetType.PANEL);
    }

    public Panel(PanelType panelType) {
        this("", WidgetType.PANEL, panelType);
    }

    public Panel(WidgetType widgetType, PanelType panelType) {
        this("", widgetType, panelType);
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
        if (overflow.requiresSize) {
            size.validate();
        }
    }

    @Override
    protected boolean acceptWidget(Widget widget) {
        return true;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        if (overflow.requiresSize) {
            StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
            style.add("width", size.width);
            style.add("height", size.height);
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

    public Overflow getOverflow() {
        return overflow;
    }

    public Panel setOverflow(Overflow overflow) {
        if (overflow == null) {
            return this;
        }
        if (this.overflow != Overflow.DEFAULT) {
            removeClassName(this.overflow.className);
        }
        this.overflow = overflow;
        addClassName(this.overflow.className);
        return this;
    }

    public Panel setOverflowAndSize(Overflow overflow, Size size) {
        setOverflow(overflow);
        setSize(size);
        return this;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
