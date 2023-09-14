package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

import java.util.HashMap;
import java.util.Map;

// todo: @PageXmlElement("")
public class DataTemplateImpl<T extends Widget> implements HasDataTemplate {
    private final T widget;
    private Widget defaultTemplateWidget;
    private final Map<Widget, ValueStatement> templateWidgets = new HashMap<>();
    private ContainerWidget<?> templateTargetWidget;
    private String dataKeyField;
    private String templateSelectorField;

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
    public Map<Widget, ValueStatement> getTemplateWidgets() {
        return templateWidgets;
    }

    @Override
    public Widget getDefaultTemplateWidget() {
        return defaultTemplateWidget;
    }

    @Override
    public void setDefaultTemplateWidget(Widget defaultTemplateWidget) {
        this.defaultTemplateWidget = defaultTemplateWidget;
    }

    @Override
    public void addTemplateWidget(Widget templateWidget, ValueStatement valueStatement) {
        if (templateWidget == null) {
            throw new IllegalArgumentException("expected template widget");
        }
        if (valueStatement == null) {
            throw new IllegalArgumentException("expected value statement");
        }
        if (defaultTemplateWidget == null) {
            setDefaultTemplateWidget(templateWidget);
        }
        this.templateWidgets.put(templateWidget, valueStatement);
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
    public String getTemplateSelectorField() {
        return templateSelectorField;
    }

    @Override
    public void setTemplateSelectorField(String templateSelectorField) {
        this.templateSelectorField = templateSelectorField;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (defaultTemplateWidget == null) {
            throw new WidgetConfigurationException("missing default template");
        }
        if (templateTargetWidget == null) {
            throw new WidgetConfigurationException("template target container not set");
        }
        if (dataKeyField == null || dataKeyField.isEmpty()) {
            throw new WidgetConfigurationException("invalid dataKeyField");
        }
        if (templateWidgets.size() > 1 && (templateSelectorField == null || templateSelectorField.isEmpty())) {
            throw new WidgetConfigurationException("invalid templateSelectorField");
        }
    }
}
