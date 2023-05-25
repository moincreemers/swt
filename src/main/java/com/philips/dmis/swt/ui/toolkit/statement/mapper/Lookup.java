package com.philips.dmis.swt.ui.toolkit.statement.mapper;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Lookup extends MapStatement {
    private final Map<Object, Object> table = new LinkedHashMap<>();

    public Lookup(Map<Object, Object> table) {
        super(JsType.OBJECT);
        this.table.putAll(table);
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(%s,%s,obj,key)=>{", ARGUMENT_SERVICE_RESPONSE, ARGUMENT_TARGET);
        js.append("const table=%s;", DtoUtil.valueOf(table));
        js.append("const defaultValue=table.hasOwnProperty('*')?table['*']:null;");
        js.append("if(!table.hasOwnProperty(key)){return defaultValue!=null?defaultValue:key;};");
        js.append("var value=table[key];");
        js.append("if(defaultValue!=null&&value==null){value=defaultValue;}");
        js.append("return value;");
        js.append("}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        //
    }

    @Override
    public void getReferences(List<Statement> statements) {
    }
}
