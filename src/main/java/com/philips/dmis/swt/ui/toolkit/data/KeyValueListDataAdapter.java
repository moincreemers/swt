package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

/**
 * A data adapter that takes an array containing objects and transforms
 * it into a list of key-value pairs.
 */
public class KeyValueListDataAdapter extends DataAdapter {
    public static final String DEFAULT_KEY_FIELD = "key";
    public static final String DEFAULT_VALUE_FIELD = "value";

    private final String keyField;
    private final String valueField;
    private final OrderBy sort;

    public KeyValueListDataAdapter() {
        this(DEFAULT_PATH, OrderBy.NONE);
    }

    public KeyValueListDataAdapter(OrderBy sort) {
        this(DEFAULT_PATH, DEFAULT_KEY_FIELD, DEFAULT_VALUE_FIELD, sort);
    }

    /**
     * Data adapter that takes a single dimension array and transforms it into a list of key-value pairs.
     *
     * @param path given a json object that provided by a data source, the path to an array that will be transformed.
     * @param sort
     */
    public KeyValueListDataAdapter(String path, OrderBy sort) {
        this(path, DEFAULT_KEY_FIELD, DEFAULT_VALUE_FIELD, sort);
    }

    public KeyValueListDataAdapter(String keyField, String valueField) {
        this(DEFAULT_PATH, keyField, valueField, OrderBy.NONE);
    }

    public KeyValueListDataAdapter(String keyField, String valueField, OrderBy sort) {
        this(DEFAULT_PATH, keyField, valueField, sort);
    }

    /**
     * Data adapter that takes an array containing objects and transforms it into a list of key-value pairs.
     *
     * @param path       given a json object that provided by a data source, the path to an array that will be transformed.
     * @param keyField   given an array element in the json object, keyField maps the value that represents the record key.
     * @param valueField given an array element in the json object, valueField maps the value that represents the record value.
     * @param sort
     */
    public KeyValueListDataAdapter(String path, String keyField, String valueField, OrderBy sort) {
        super(path, false);
        this.keyField = keyField;
        this.valueField = valueField;
        this.sort = sort;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse)=>{");

        js.append("let data=serviceResponse%s;", getPath());
        js.append("let o=[];");
        if (keyField.isEmpty() && valueField.isEmpty()) {
            js.append("for(const i in data){o[i]={key:data[i],value:data[i]};};");
        } else {
            js.append("for(const i in data){o[i]={key:data[i]['%s'],value:data[i]['%s']};};", keyField, valueField);
        }
        switch (sort) {
            case BY_KEY:
                js.append("o.sort((a, b)=>{");
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
                js.append("o.sort((a, b)=>{");
                js.append("const a0=Number.isNaN(a)?a.key.toString().toUpperCase():a;");
                js.append("const b0=Number.isNaN(b)?b.key.toString().toUpperCase():b;");
                js.append("if(a0<b0){return -1;}");
                js.append("if(a0>b0){return 1;}");
                js.append("return 0;");
                js.append("}");
                js.append(");");
                break;
        }
        js.append("return o;");

        js.append("}"); // end function
    }
}
