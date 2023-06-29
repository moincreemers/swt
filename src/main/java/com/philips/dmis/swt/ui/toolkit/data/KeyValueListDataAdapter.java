package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.dto.TransformationMetadata;
import com.philips.dmis.swt.ui.toolkit.dto.View;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.CopyViewFieldFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.CreateViewFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceUsage;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

/**
 * A data adapter that transforms the data set into a list of key-value pairs.
 */
public class KeyValueListDataAdapter extends DataAdapter {
    public static final String OUTPUT_KEY_FIELD = "__key__";
    public static final String OUTPUT_VALUE_FIELD = "__value__";

    private static final String OUTPUT_KEY_FIELD_NAME = "Key";
    private static final String OUPUT_VALUE_FIELD_NAME = "Value";

    public static final String DEFAULT_KEY_FIELD = "key";
    public static final String DEFAULT_VALUE_FIELD = "value";

    private final String keyField;
    private final String valueField;
    private final boolean distinct;

    public KeyValueListDataAdapter() {
        this(DEFAULT_PATH);
    }

    /**
     * Data adapter that takes a single dimension array and transforms it into a list of key-value pairs.
     *
     * @param path given a json object that provided by a data source, the path to an array that will be transformed.
     */
    public KeyValueListDataAdapter(String path) {
        this(path, DEFAULT_KEY_FIELD, DEFAULT_VALUE_FIELD, true);
    }

    public KeyValueListDataAdapter(String keyField, String valueField) {
        this(DEFAULT_PATH, keyField, valueField, true);
    }

    public KeyValueListDataAdapter(String keyField, String valueField, boolean distinct) {
        this(DEFAULT_PATH, keyField, valueField, distinct);
    }

    public KeyValueListDataAdapter(String path, String keyField, String valueField) {
        this(path, keyField, valueField, true);
    }

    /**
     * Data adapter that takes an array containing objects and transforms it into a list of key-value pairs.
     *
     * @param path       given a json object that provided by a data source, the path to an array that will be transformed.
     * @param keyField   given an array element in the json object, keyField maps the value that represents the record key.
     * @param valueField given an array element in the json object, valueField maps the value that represents the record value.
     * @param distinct   if true, only returns distinct values
     */
    public KeyValueListDataAdapter(String path, String keyField, String valueField, boolean distinct) {
        super(path, false);
        if (keyField == null || keyField.isEmpty()) {
            throw new IllegalArgumentException("invalid key field");
        }
        if (valueField == null || valueField.isEmpty()) {
            throw new IllegalArgumentException("invalid value field");
        }
        this.keyField = keyField;
        this.valueField = valueField;
        this.distinct = distinct;
    }

    @Override
    public DataSourceUsage getInitialDataSourceUsage() {
        return DataSourceUsage.ITEMS;
    }

    @Override
    public boolean isDataSourceUsageAllowed(DataSourceUsage dataSourceUsage) {
        return dataSourceUsage == getInitialDataSourceUsage();
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse,unmodifiedResponse)=>{");
        js.trace(this);

        // re-create data structure
        js.append("const output=structuredClone(serviceResponse);");
        js.append("var items=output%s;", getPath());

        String rootViewId = View.getRootViewId(getId());
        js.append("const viewTop=%s(output,'%s','%s',false,false);",
                JsGlobalModule.getQualifiedId(CreateViewFunction.class),
                rootViewId,
                getClass().getSimpleName());

        js.append("%s(output,viewTop,'%s','%s','%s');",
                JsGlobalModule.getQualifiedId(CopyViewFieldFunction.class),
                keyField,
                OUTPUT_KEY_FIELD_NAME,
                OUTPUT_KEY_FIELD
        );
        js.append("%s(output,viewTop,'%s','%s','%s');",
                JsGlobalModule.getQualifiedId(CopyViewFieldFunction.class),
                valueField,
                OUPUT_VALUE_FIELD_NAME,
                OUTPUT_VALUE_FIELD
        );

        // copy records and add output fields

        js.append("const keys=[];");
        js.append("const newItems=[];");
        js.append("for(const i in items){"); // for
        js.append("var item=items[i];");
        js.append("var key=item['%s'];", keyField);
        if (distinct) {
            js.append("if(keys.includes(key)){");
            js.append("continue;");
            js.append("};");
            js.append("keys.push(key);");
        }
        js.append("item['%s']=item['%s'];", OUTPUT_KEY_FIELD, keyField);
        js.append("item['%s']=item['%s'];", OUTPUT_VALUE_FIELD, valueField);
        js.trace("console.log('append item', i, item);");
        js.append("newItems.push(item);");
        js.append("};"); // end for

        js.append("output%s=newItems;", getPath());

        js.append("output.meta['%s']='%s';",
                ServiceResponse.META_SELECTED_VIEW_ID, rootViewId);

        js.append("output.meta['%s']=Object.assign([],output.meta['%s']);",
                ServiceResponse.META_TRANSFORMATIONS, ServiceResponse.META_TRANSFORMATIONS);
        js.append("output.meta['%s'].push(%s);",
                ServiceResponse.META_TRANSFORMATIONS,
                DtoUtil.valueOf(new TransformationMetadata(getId(), getClass().getSimpleName())));

        js.trace("console.log('KeyValueListDataAdapter,after',output);");

        js.append("return output;");

        js.append("}"); // end function
    }
}
