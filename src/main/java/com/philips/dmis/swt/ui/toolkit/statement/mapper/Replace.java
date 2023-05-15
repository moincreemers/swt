package com.philips.dmis.swt.ui.toolkit.statement.mapper;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

public class Replace extends MapStatement {
    final ValueStatement search;
    final ValueStatement replace;

    public Replace(ValueStatement search,
                   ValueStatement replace) {
        super(JsType.OBJECT);
        this.search = search;
        this.replace = replace;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(value)=>{");
        js.append("if(value==undefined){return value;};");
        js.append("return (%s==value)?%s:value;",
                ValueStatement.valueOf(toolkit, search, widget),
                ValueStatement.valueOf(toolkit, replace, widget));
        js.append("}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
