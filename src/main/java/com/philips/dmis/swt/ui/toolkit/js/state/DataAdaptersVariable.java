package com.philips.dmis.swt.ui.toolkit.js.state;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.*;

public class DataAdaptersVariable implements JsVariable {
    public static final String DATASOURCE_ID = "self";
    public static final String ID = "dataAdapters";
    private final Widget widget;
    private final WidgetType widgetType;

    public DataAdaptersVariable(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof DataSourceSupplier || widget instanceof DataBoundWidget<?, ?>;
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
        Map<DataSourceUsage, Map<String, List<DataAdapter>>> dataAdaptersByUsageAndDataSourceId = new HashMap<>();
        for (DataSourceUsage dataSourceUsage : DataSourceUsage.valuesByPriority()) {
            dataAdaptersByUsageAndDataSourceId.put(dataSourceUsage, new HashMap<>());
        }
        if (widget instanceof DataSourceSupplier dataSourceSupplier) {
            for (DataAdapter dataAdapter : dataSourceSupplier.getDataAdapters()) {
                addDataAdapters(dataAdaptersByUsageAndDataSourceId, dataAdapter.getInitialDataSourceUsage(),
                        DATASOURCE_ID, List.of(dataAdapter));
            }
        }
        if (widget instanceof HasDataSource<?, ?> hasDataSource) {
            for (DataSourceUsage dataSourceUsage : DataSourceUsage.valuesByPriorityForDataConsumer()) {
                Map<String, DataSource> ds = hasDataSource.getDataSources(dataSourceUsage);
                if (ds == null) {
                    continue;
                }
                for (String dataSourceId : ds.keySet()) {
                    DataSource dataSource = ds.get(dataSourceId);
                    addDataAdapters(dataAdaptersByUsageAndDataSourceId, dataSourceUsage, dataSourceId, dataSource.getDataAdapters());
                }
            }
        }

        js.append("{");

        int i = 0;
        for (DataSourceUsage dataSourceUsage : dataAdaptersByUsageAndDataSourceId.keySet()) {
            if (i > 0) {
                js.append(",");
            }
            js.append("%s:{", dataSourceUsage.name());
            Map<String, List<DataAdapter>> dataAdaptersByDataSourceId = dataAdaptersByUsageAndDataSourceId.get(dataSourceUsage);
            int k = 0;
            for (String dataSourceId : dataAdaptersByDataSourceId.keySet()) {
                if (k > 0) {
                    js.append(",");
                }
                js.append("'%s':[", dataSourceId);
                List<DataAdapter> dataAdapters = dataAdaptersByDataSourceId.get(dataSourceId);
                int j = 0;
                for (DataAdapter dataAdapter : dataAdapters) {
                    if (j > 0) {
                        js.append(",");
                    }
                    js.append("{");
                    js.append("id:'%s',", dataAdapter.getId());
                    js.append("type:'%s',", dataAdapter.getClass().getSimpleName());
                    js.append("fn:");
                    dataAdapter.renderJs(toolkit, widget, js);
                    js.append("}");
                    j++;
                }
                js.append("]");
                k++;
            }
            js.append("}");
            i++;
        }

        js.append("}");
    }

    void addDataAdapters(Map<DataSourceUsage, Map<String, List<DataAdapter>>> dataAdaptersByUsageAndDataSourceId,
                         DataSourceUsage dataSourceUsage, String dataSourceId, List<DataAdapter> dataAdapters) {
        dataAdaptersByUsageAndDataSourceId
                .computeIfAbsent(dataSourceUsage, k -> new HashMap<>())
                .computeIfAbsent(dataSourceId, k -> new ArrayList<>())
                .addAll(dataAdapters.stream()
                        .filter(dataAdapter -> dataAdapter.isDataSourceUsageAllowed(dataSourceUsage)).toList());
    }
}
