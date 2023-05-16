package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceUsage;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

public abstract class DataAdapter {
    public static final String DEFAULT_PATH = ".data.items";

    static String validatePath(String path) {
        if (path.contains("[") || path.contains("]")) {
            throw new IllegalArgumentException("indexed paths not supported");
        }
        if (!path.isEmpty() && !path.startsWith(".")) {
            throw new IllegalArgumentException("path must start with .");
        }
        return path;
    }

    static String inferFieldNameFromPath(String path) {
        String[] fields = path.split("\\.");
        if (fields.length == 0) {
            throw new IllegalArgumentException("could not infer field name from path: " + path);
        }
        return fields[fields.length - 1];
    }

    private static int idCounter = 0;
    private final String id;

    private final String path;
    private final String fieldName;
    private boolean validated;

    public DataAdapter(String path) {
        this(path, true);
    }

    public DataAdapter(String path, boolean inferFieldName) {
        this(path, inferFieldName ? inferFieldNameFromPath(path) : "");
    }

    public DataAdapter(String path, String fieldName) {
        id = "a" + idCounter++;
        this.path = validatePath(path);
        this.fieldName = fieldName;
    }

    public final String getId() {
        return id;
    }

    /**
     * Returns true if the data adapter can be used for the specified data source usage.
     *
     * @param dataSourceUsage
     * @return
     */
    public boolean isDataSourceUsage(DataSourceUsage dataSourceUsage) {
        return dataSourceUsage!=DataSourceUsage.IMPORT;
    }

    public String getPath() {
        return path;
    }

    public String getMemberName() {
        String m = path;
        if (m.startsWith(".")) {
            m = m.substring(1);
        }
        return m;
    }

    public String[] getObjectNames() {
        return getMemberName().split("\\.");
    }

    public String getFieldName() {
        return fieldName;
    }

    public abstract void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException;

    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
    }
}
