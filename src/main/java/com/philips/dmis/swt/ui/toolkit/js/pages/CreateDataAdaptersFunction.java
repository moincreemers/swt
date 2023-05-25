package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.ArrayList;
import java.util.List;

public class CreateDataAdaptersFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "createDataAdapters";
    private final Widget widget;
    private final WidgetType widgetType;

    public CreateDataAdaptersFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof DataSourceSupplier || widget instanceof DataBoundWidget<?>;
    }

    @Override
    public boolean isPublic() {
        return true;
    }

    @Override
    public String getPublicName(String id) {
        return id;
    }

    @Override
    public JsType getType() {
        return JsType.VOID;
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
        dependencies.add(DataAdaptersVariable.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("()=>{");

        js.debug("console.log('CreateDataAdaptersFunction','%s');", widget.getId());

        if (widget instanceof HasDataSource<?> hasDataSource) {
            // this is the case for widgets that USE a data source
            for (DataSourceUsage dataSourceUsage : DataSourceUsage.values()) {
                if (!hasDataSource.hasDataSource(dataSourceUsage)) {
                    continue;
                }
                List<DataSource> ds = hasDataSource.getDataSource(dataSourceUsage);
                List<DataAdapter> dataAdapters = new ArrayList<>();
                for (DataSource dataSource : ds) {
                    dataAdapters.addAll(dataSource.getDataAdapters());
                }
                generateDataAdapters(toolkit, dataSourceUsage, dataAdapters, js);
            }
        } else if (widget instanceof DataSourceSupplier dataSourceSupplier) {
            // this is the case for widgets that ARE a data source
            generateDataAdapters(toolkit, DataSourceUsage.IMPORT, dataSourceSupplier.getDataAdapters(), js);
            generateDataAdapters(toolkit, DataSourceUsage.TRANSFORM, dataSourceSupplier.getDataAdapters(), js);
        }

        js.append("}");
    }

    void generateDataAdapters(Toolkit toolkit, DataSourceUsage dataSourceUsage,
                              List<DataAdapter> dataAdapters, JsWriter js) {
        // generate and push each data adapter function into a state variable

        js.debug("console.log('%s','create %d data adapters for: %s');",
                widget.getId(), dataAdapters.size(), dataSourceUsage.name());

        js.append("var adapters=Object.assign([],%s['%s']);",
                JsPagesModule.getId(widget, DataAdaptersVariable.class),
                dataSourceUsage.name());
        for (DataAdapter dataAdapter : dataAdapters) {
            if (dataAdapter.isDataSourceUsage(dataSourceUsage)) {
                js.debug("console.log('adding data adapter: %s, %s');", dataAdapter.getId(), dataAdapter.getClass().getSimpleName());
                js.append("adapters.push({");
                js.append("id:'%s',", dataAdapter.getId());
                js.append("type:'%s',", dataAdapter.getClass().getSimpleName());
                js.append("fn:");
                dataAdapter.renderJs(toolkit, widget, js);
                js.append("});");
            }
        }
        js.append("%s['%s']=adapters;",
                JsPagesModule.getId(widget, DataAdaptersVariable.class),
                dataSourceUsage.name());
    }
}
