package com.philips.dmis.swt.ui.toolkit.statement.reduce;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

public class Sum extends ReducerStatement {
    public Sum() {
        super(JsType.NUMBER);
    }

    @Override
    public Object getInitialValue() {
        return 0;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(%s,%s)=>{", ARGUMENT_LEFT, ARGUMENT_RIGHT);
        js.append("return %s+%s;", ARGUMENT_LEFT, ARGUMENT_RIGHT);
        js.append("}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
