package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.AppendEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.InputEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.RemoveEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class ContainerWidget<W extends ContainerWidget<? extends Widget>> extends Widget implements
        Iterable<Widget>, HasName<ContainerWidget<W>> {
    protected final java.util.List<Widget> widgets = new ArrayList<>();

    public ContainerWidget(WidgetType widgetType) {
        this("", widgetType);
    }

    public ContainerWidget(String name, WidgetType widgetType) {
        super(widgetType);
        setName(name);
    }

    public ContainerWidget<W> onAppend(AppendEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public ContainerWidget<W> onRemove(RemoveEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public W onInput(InputEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return (W) this;
    }

    public W onChange(ChangeEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return (W) this;
    }

    public <T extends Widget> T[] addAll(T... widgets) throws WidgetConfigurationException {
        if (widgets == null || widgets.length == 0) {
            return widgets;
        }
        for (Widget widget : widgets) {
            add(widget);
        }
        return widgets;
    }

    public <T extends Widget> T add(T widget) throws WidgetConfigurationException {
        return addUnsafe(widget, true);
    }

    public <T extends HasWidgets> T add(T hasWidgets) throws WidgetConfigurationException {
        try {
            for (Widget widget : hasWidgets.getWidgets()) {
                add(widget);
            }
        } catch (Exception e) {
            throw new WidgetConfigurationException("composite build error", e);
        }
        return (T) hasWidgets;
    }

    protected <T extends Widget> T addUnsafe(T widget, boolean addSiblings) throws WidgetConfigurationException {
        if (!allowChild(widget)) {
            throw new WidgetConfigurationException(String.format("%s is not allowed in a %s", widget.getWidgetType().name(), getWidgetType().name()));
        }
        if (widget.isParentSet()) {
            widget.getParent().remove(widget);
        }
        widget.setParent(this);
        widgets.add(widget);
        if (addSiblings) {
            for (Widget sibling : widget.getSiblings()) {
                sibling.setParent(this);
                widgets.add(sibling);
            }
        }
        return widget;
    }

    protected <T extends Widget> boolean allowChild(T widget) {
        return true;
    }

    public void remove(Widget widget) throws WidgetConfigurationException {
        widgets.remove(widget);
        widget.setParent(null);
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        super.renderJs(toolkit, js);
        for (Widget widget : widgets) {
            widget.renderJs(toolkit, js);
        }
    }

    @Override
    public Iterator<Widget> iterator() {
        return widgets.iterator();
    }

    public Collection<Widget> getWidgets() {
        return widgets;
    }

    @Override
    public void pack() throws Exception {
        super.pack();
        for (Widget widget : widgets) {
            widget.pack();
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        super.validate(toolkit);
        for (Widget widget : widgets) {
            if (!acceptWidget(widget)) {
                throw new WidgetConfigurationException(
                        String.format("widget %s not allowed on page %s (%s)",
                                widget.widgetType, getId(), widgetType));
            }
            widget.validate(toolkit);
        }
    }

    protected abstract boolean acceptWidget(Widget widget);

    // NAME

    private final NameImpl<ContainerWidget<W>> nameImpl = new NameImpl<>(this);

    @Override
    public HasName<ContainerWidget<W>> getNameImpl() {
        return nameImpl;
    }

    @Override
    public String getName() {
        return nameImpl.getName();
    }

    @Override
    public ContainerWidget<W> setName(String name) {
        nameImpl.setName(name);
        return this;
    }
}
