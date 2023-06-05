package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceUsage;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

/**
 * Given a service response object provided by a data source,
 * this Data Adapter returns the specified field from the
 * n-th record which is the first record by default.
 * <p>
 * Note that:
 * <ol>
 * <li>A response that contains a record is transmitted as the first element in an array.</li>
 * <li>A response that contains a single (primitive) value is transmitted as an object with
 *  the property name 'value' containing the value.</li>
 * </ol>
 */
public class PathDataAdapter extends DataAdapter {
    public static final int DEFAULT_RECORD_INDEX = 0;
    public static final String DEFAULT_FIELD = "value";

    private final String field;
    private final int recordIndex;

    public PathDataAdapter() {
        this(DEFAULT_PATH, DEFAULT_FIELD, DEFAULT_RECORD_INDEX);
    }

    public PathDataAdapter(String field) {
        this(DEFAULT_PATH, field, DEFAULT_RECORD_INDEX);
    }

    public PathDataAdapter(String path, String field, int recordIndex) {
        super(path);
        this.field = field;
        this.recordIndex = recordIndex;
    }

    @Override
    public boolean isDataSourceUsage(DataSourceUsage dataSourceUsage) {
        return dataSourceUsage == DataSourceUsage.VALUE || dataSourceUsage == DataSourceUsage.TEXT;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse,unmodifiedResponse)=>{");
        js.trace(this);

        js.append("const items=serviceResponse%s;", getPath());
        js.append("if(items==null||items==undefined){");
        js.throwError("path not found", "serviceResponse");
        js.append("};");
        js.append("const recordIndex=%d;", recordIndex);
        js.append("if(recordIndex>=items.length){");
        js.throwError("record not found", "items", "recordIndex");
        js.append("};");
        js.append("const record=items[recordIndex];");
        js.append("const fieldName='%s';", field);
        js.append("if(!record.hasOwnProperty(fieldName)){");
        js.throwError("property not found", "items", "fieldName");
        js.append("};");
        js.append("return record[fieldName];");

        js.append("}");
    }
}
