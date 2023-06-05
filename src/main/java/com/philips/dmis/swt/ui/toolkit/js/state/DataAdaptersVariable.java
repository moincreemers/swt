package com.philips.dmis.swt.ui.toolkit.js.state;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.ArrayList;
import java.util.List;

public class DataAdaptersVariable implements JsVariable {
    public static final String ID = "dataAdapters";
    private final Widget widget;
    private final WidgetType widgetType;

    public DataAdaptersVariable(Widget widget) {
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
        return JsType.OBJECT;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("{");
        if (widget instanceof HasDataSource<?> hasDataSource) {
            // this is the case for widgets that USE a data source
            int i = 0;
            for (DataSourceUsage dataSourceUsage : DataSourceUsage.values()) {
                if (i > 0) {
                    js.append(",");
                }
                js.append("%s:[", dataSourceUsage.name());
                if (hasDataSource.hasDataSource(dataSourceUsage)) {
                    List<DataSource> ds = hasDataSource.getDataSources(dataSourceUsage);
                    List<DataAdapter> dataAdapters = new ArrayList<>();
                    for (DataSource dataSource : ds) {
                        dataAdapters.addAll(dataSource.getDataAdapters());
                    }
                    generateDataAdapters(toolkit, dataSourceUsage, dataAdapters, js);
                }
                js.append("]");
                i++;
            }
        } else if (widget instanceof DataSourceSupplier dataSourceSupplier) {
            // this is the case for widgets that ARE a data source
            js.append("%s:[", DataSourceUsage.IMPORT.name());
            generateDataAdapters(toolkit, DataSourceUsage.IMPORT, dataSourceSupplier.getDataAdapters(), js);
            js.append("],");
            js.append("%s:[", DataSourceUsage.TRANSFORM.name());
            generateDataAdapters(toolkit, DataSourceUsage.TRANSFORM, dataSourceSupplier.getDataAdapters(), js);
            js.append("]");
        }
        js.append("}");
    }

    void generateDataAdapters(Toolkit toolkit, DataSourceUsage dataSourceUsage,
                              List<DataAdapter> dataAdapters, JsWriter js) {
        int i = 0;
        for (DataAdapter dataAdapter : dataAdapters) {
            if (dataAdapter.isDataSourceUsage(dataSourceUsage)) {
                if (i > 0) {
                    js.append(",");
                }
                js.append("{");
                js.append("id:'%s',", dataAdapter.getId());
                js.append("type:'%s',", dataAdapter.getClass().getSimpleName());
                js.append("fn:");
                dataAdapter.renderJs(toolkit, widget, js);
                js.append("}");
                i++;
            }
        }
    }
}
