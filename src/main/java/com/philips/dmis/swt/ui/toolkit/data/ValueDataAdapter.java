package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceUsage;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

/**
 * Given a data set (ServiceResponse) that contains a path to an array of objects (records),
 * this data adapter returns a similar data set but modifies each object in the array and maps a specified field
 * to a new field called '__value__' which all data bound widgets use to extract a single value from a record.
 */
public class ValueDataAdapter extends DataAdapter {
    public static final String OUTPUT_FIELD_NAME = "__value__";
    public static final String DEFAULT_FIELD = "value";

    private final String field;

    public ValueDataAdapter() {
        this(DEFAULT_PATH, DEFAULT_FIELD);
    }

    public ValueDataAdapter(String field) {
        this(DEFAULT_PATH, field);
    }

    public ValueDataAdapter(String path, String field) {
        super(path);
        this.field = field;
    }

    @Override
    public boolean isDataSourceUsage(DataSourceUsage dataSourceUsage) {
        return dataSourceUsage == DataSourceUsage.VALUE || dataSourceUsage == DataSourceUsage.TEXT;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse,unmodifiedResponse)=>{");
        js.trace(this);

        js.append("const output=structuredClone(serviceResponse);");
        js.append("const items=output%s;", getPath());
        js.append("if(items==null||items==undefined){");
        js.throwError("path not found", "serviceResponse");
        js.append("};");

        js.append("for(const i in items){"); // for
        js.append("var record=items[i];");
        js.append("const fieldName='%s';", field);
        js.append("if(!record.hasOwnProperty(fieldName)){");
        js.throwError("property not found", "items", "fieldName");
        js.append("};");
        js.append("record['%s']=record[fieldName];", OUTPUT_FIELD_NAME);
        js.append("};"); // end for

        js.info("console.log('%s',output);", getClass().getSimpleName());
        js.append("return output;");

        js.append("}");
    }
}
