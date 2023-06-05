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
    public static final String DEFAULT_KEY_FIELD = "key";
    public static final String DEFAULT_KEY_FIELD_NAME = "Key";
    public static final String DEFAULT_VALUE_FIELD = "value";
    public static final String DEFAULT_VALUE_FIELD_NAME = "Value";

    private final String keyField;
    private final String valueField;
    private final OrderBy sort;
    private final boolean distinct;

    public KeyValueListDataAdapter() {
        this(DEFAULT_PATH, OrderBy.NONE);
    }

    public KeyValueListDataAdapter(OrderBy sort) {
        this(DEFAULT_PATH, DEFAULT_KEY_FIELD, DEFAULT_VALUE_FIELD, sort, true);
    }

    /**
     * Data adapter that takes a single dimension array and transforms it into a list of key-value pairs.
     *
     * @param path given a json object that provided by a data source, the path to an array that will be transformed.
     * @param sort
     */
    public KeyValueListDataAdapter(String path, OrderBy sort) {
        this(path, DEFAULT_KEY_FIELD, DEFAULT_VALUE_FIELD, sort, true);
    }

    public KeyValueListDataAdapter(String keyField, String valueField) {
        this(DEFAULT_PATH, keyField, valueField, OrderBy.NONE, true);
    }

    public KeyValueListDataAdapter(String keyField, String valueField, OrderBy sort) {
        this(DEFAULT_PATH, keyField, valueField, sort, true);
    }

    public KeyValueListDataAdapter(String path, String keyField, String valueField, OrderBy sort) {
        this(path, keyField, valueField, sort, true);
    }

    /**
     * Data adapter that takes an array containing objects and transforms it into a list of key-value pairs.
     *
     * @param path       given a json object that provided by a data source, the path to an array that will be transformed.
     * @param keyField   given an array element in the json object, keyField maps the value that represents the record key.
     * @param valueField given an array element in the json object, valueField maps the value that represents the record value.
     * @param sort       how to sort the list
     * @param distinct   if true, only returns distinct values
     */
    public KeyValueListDataAdapter(String path, String keyField, String valueField, OrderBy sort, boolean distinct) {
        super(path, false);
        this.keyField = keyField;
        this.valueField = valueField;
        this.sort = sort;
        this.distinct = distinct;
    }

    public boolean isDataSourceUsage(DataSourceUsage dataSourceUsage) {
        return dataSourceUsage == DataSourceUsage.OPTIONS
                || dataSourceUsage == DataSourceUsage.LIST_ITEMS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse,unmodifiedResponse)=>{");
        js.trace(this);

        // re-create data structure
        js.append("const output=structuredClone(serviceResponse);");
        js.append("var m=output%s;", getPath());
        js.append("m.length=0;");

        js.append("const data=serviceResponse%s;", getPath());

        String rootViewId = View.getRootViewId(getId());
        js.append("const viewTop=%s(output,'%s','%s',false,false);",
                JsGlobalModule.getQualifiedId(CreateViewFunction.class),
                rootViewId,
                getClass().getSimpleName());

        js.append("const keys=[];");
        if (keyField.isEmpty() && valueField.isEmpty()) {
            js.append("for(const i in data){");
            if (distinct) {
                js.append("if(keys.includes(data[i])){continue;};");
            }
            js.append("keys.push(data[i]);");
            js.append("m.push({key:data[i],value:data[i]});");
            js.append("};");
        } else {
            js.append("%s(output,viewTop,'%s','%s','%s');",
                    JsGlobalModule.getQualifiedId(CopyViewFieldFunction.class),
                    keyField,
                    DEFAULT_KEY_FIELD_NAME,
                    DEFAULT_KEY_FIELD
            );
            js.append("%s(output,viewTop,'%s','%s','%s');",
                    JsGlobalModule.getQualifiedId(CopyViewFieldFunction.class),
                    valueField,
                    DEFAULT_VALUE_FIELD_NAME,
                    DEFAULT_VALUE_FIELD
            );
            js.append("for(const i in data){");
            if (distinct) {
                js.append("if(keys.includes(data[i]['%s'])){continue;};", keyField);
            }
            js.append("keys.push(data[i]['%s']);", keyField);
            js.append("m.push({key:data[i]['%s'],value:data[i]['%s']});", keyField, valueField);
            js.append("};");

        }
        switch (sort) {
            case BY_KEY:
                js.append("m.sort((a, b)=>{");
                js.append("const a0=Number.isNaN(a)?a.key.toString().toUpperCase():a;");
                js.append("const b0=Number.isNaN(b)?b.key.toString().toUpperCase():b;");
                js.append("if(a0<b0){");
                js.append("return -1;");
                js.append("}");
                js.append("if(a0>b0){return 1;}");
                js.append("return 0;");
                js.append("}");
                js.append(");");
                break;
            case BY_VALUE:
                js.append("m.sort((a, b)=>{");
                js.append("const a0=Number.isNaN(a)?a.key.toString().toUpperCase():a;");
                js.append("const b0=Number.isNaN(b)?b.key.toString().toUpperCase():b;");
                js.append("if(a0<b0){return -1;}");
                js.append("if(a0>b0){return 1;}");
                js.append("return 0;");
                js.append("}");
                js.append(");");
                break;
        }

        js.append("output.meta['%s']='%s';",
                ServiceResponse.META_SELECTED_VIEW_ID, rootViewId);

        js.append("output.meta['%s']=Object.assign([],output.meta['%s']);",
                ServiceResponse.META_TRANSFORMATIONS, ServiceResponse.META_TRANSFORMATIONS);
        js.append("output.meta['%s'].push(%s);",
                ServiceResponse.META_TRANSFORMATIONS,
                DtoUtil.valueOf(new TransformationMetadata(getId(), getClass().getSimpleName())));

        js.debug("console.log('KeyValueListDataAdapter,after',output);");

        js.append("return output;");

        js.append("}"); // end function
    }
}
