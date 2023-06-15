package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public class DataTemplateImpl<T extends Widget> implements HasDataTemplate {
    private final T widget;
    private Widget templateWidget;
    private ContainerWidget<?> templateTargetWidget;
    private String dataKeyField;

    public DataTemplateImpl(T widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasDataTemplate getHasDataTemplateImpl() {
        return this;
    }

    @Override
    public Widget getTemplateWidget() {
        return templateWidget;
    }

    @Override
    public void setTemplateWidget(Widget templateWidget) {
        this.templateWidget = templateWidget;
    }

    @Override
    public ContainerWidget<?> getTemplateTargetWidget() {
        return templateTargetWidget;
    }

    @Override
    public void setTemplateTargetWidget(ContainerWidget<?> templateTargetWidget) {
        this.templateTargetWidget = templateTargetWidget;
    }

    @Override
    public String getDataKeyField() {
        return dataKeyField;
    }

    @Override
    public void setDataKeyField(String dataKeyField) {
        this.dataKeyField = dataKeyField;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (templateWidget == null) {
            throw new WidgetConfigurationException("template not set");
        }
        if (templateTargetWidget == null) {
            throw new WidgetConfigurationException("template target container not set");
        }
        if (dataKeyField == null || dataKeyField.isEmpty()) {
            throw new WidgetConfigurationException("invalid dataKeyField");
        }
    }
}
