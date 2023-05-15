package com.philips.dmis.swt.ui.toolkit.statement.mapper;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

public class ParseInt extends MapStatement {
    public ParseInt() {
        super(JsType.NUMBER);
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(value)=>{");
        js.append("if(value==undefined||value==null||value==''||value==NaN){return null;};");
        js.append("const i=Number.parseInt(value);");
        js.append("return i==NaN?0:i;");
        js.append("}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
