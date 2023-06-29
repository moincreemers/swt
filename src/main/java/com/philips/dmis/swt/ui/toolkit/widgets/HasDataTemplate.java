package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

import java.util.Map;

public interface HasDataTemplate extends HasStaticHTML {
    HasDataTemplate getHasDataTemplateImpl();

    Map<Widget, ValueStatement> getTemplateWidgets();

    void setDefaultTemplateWidget(Widget templateWidget);

    Widget getDefaultTemplateWidget();

    void addTemplateWidget(Widget templateWidget, ValueStatement valueStatement);

    String getDataKeyField();

    void setDataKeyField(String dataKeyField);

    String getTemplateSelectorField();

    void setTemplateSelectorField(String templateSelectorField);

    ContainerWidget<?> getTemplateTargetWidget();

    void setTemplateTargetWidget(ContainerWidget<?> templateTargetWidget);
}
