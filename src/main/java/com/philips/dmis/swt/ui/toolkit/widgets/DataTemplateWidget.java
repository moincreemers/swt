package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.Map;

public class DataTemplateWidget extends DataBoundWidget<DataTemplateWidget, TemplateListItemsDataSourceUsage>
        implements HasDataTemplate {
    public DataTemplateWidget() {
        super(WidgetType.DATA_TEMPLATE);
    }

    public DataTemplateWidget addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(TemplateListItemsDataSourceUsage.LIST_ITEMS_TEMPLATE, dataSourceSupplier, dataAdapters);
        return this;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
        style.add("display", "none");
    }

    // DATA TEMPLATE

    private final DataTemplateImpl<DataTemplateWidget> dataTemplateImpl = new DataTemplateImpl<>(this);

    @Override
    public HasDataTemplate getHasDataTemplateImpl() {
        return dataTemplateImpl;
    }

    @Override
    public Widget getTemplateWidget() {
        return dataTemplateImpl.getTemplateWidget();
    }

    @Override
    public void setTemplateWidget(Widget templateWidget) {
        dataTemplateImpl.setTemplateWidget(templateWidget);
    }

    @Override
    public ContainerWidget<?> getTemplateTargetWidget() {
        return dataTemplateImpl.getTemplateTargetWidget();
    }

    @Override
    public void setTemplateTargetWidget(ContainerWidget<?> templateTargetWidget) {
        dataTemplateImpl.setTemplateTargetWidget(templateTargetWidget);
    }

    @Override
    public String getDataKeyField() {
        return dataTemplateImpl.getDataKeyField();
    }

    @Override
    public void setDataKeyField(String dataKeyField) {
        dataTemplateImpl.setDataKeyField(dataKeyField);
    }
}
