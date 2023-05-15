package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

/**
 * A data adapter that takes an array containing objects and transforms
 * it into a list of key-value pairs.
 */
public class ArrayDataAdapter extends DataAdapter {
    private final OrderBy sort;

    public ArrayDataAdapter() {
        this(DEFAULT_PATH, OrderBy.NONE);
    }

    public ArrayDataAdapter(OrderBy sort) {
        this(DEFAULT_PATH, sort);
    }


    /**
     * Data adapter that takes an array containing objects and transforms it into a list of key-value pairs.
     *
     * @param path given a json object that provided by a data source, the path to an array that will be transformed.
     * @param sort
     */
    public ArrayDataAdapter(String path, OrderBy sort) {
        super(path, false);
        this.sort = sort;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(serviceResponse)=>{");

        js.append("let data=serviceResponse%s;", getPath());
        js.append("let o=[];");
        js.append("for(const i in data){o[i]={key:data[i],value:data[i]};};");
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
