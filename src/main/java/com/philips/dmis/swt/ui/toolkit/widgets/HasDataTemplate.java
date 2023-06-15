package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasDataTemplate extends HasStaticHTML {
    HasDataTemplate getHasDataTemplateImpl();

    Widget getTemplateWidget();

    void setTemplateWidget(Widget templateWidget);

    String getDataKeyField();

    void setDataKeyField(String dataKeyField);

    ContainerWidget<?> getTemplateTargetWidget();

    void setTemplateTargetWidget(ContainerWidget<?> templateTargetWidget);
}
