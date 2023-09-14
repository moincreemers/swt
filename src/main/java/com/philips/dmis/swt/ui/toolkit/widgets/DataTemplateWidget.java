package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

import java.util.Map;

public class DataTemplateWidget extends DataBoundWidget<DataTemplateWidget, ItemsDataSourceUsage>
        implements HasDataTemplate {
    public DataTemplateWidget(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.DATA_TEMPLATE);
    }

    public DataTemplateWidget() {
        super(WidgetType.DATA_TEMPLATE);
    }

    public DataTemplateWidget addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ItemsDataSourceUsage.ITEMS, dataSourceSupplier, dataAdapters);
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
    public Map<Widget, ValueStatement> getTemplateWidgets() {
        return dataTemplateImpl.getTemplateWidgets();
    }

    @Override
    public Widget getDefaultTemplateWidget() {
        return dataTemplateImpl.getDefaultTemplateWidget();
    }

    @Override
    public void setDefaultTemplateWidget(Widget templateWidget) {
        dataTemplateImpl.setDefaultTemplateWidget(templateWidget);
    }

    @Override
    public void addTemplateWidget(Widget templateWidget, ValueStatement valueStatement) {
        dataTemplateImpl.addTemplateWidget(templateWidget, valueStatement);
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

    @Override
    public String getTemplateSelectorField() {
        return dataTemplateImpl.getTemplateSelectorField();
    }

    @Override
    public void setTemplateSelectorField(String templateSelectorField) {
        dataTemplateImpl.setTemplateSelectorField(templateSelectorField);
    }
}
