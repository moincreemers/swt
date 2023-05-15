package com.philips.dmis.swt.ui.toolkit.statement.reduce;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class CodeReducer extends ReducerStatement {
    private final String js;
    private final Object initialValue;

    public CodeReducer(String js, JsType jsType, Object initialValue) {
        super(jsType);
        this.js = js;
        this.initialValue = initialValue;
    }

    @Override
    public Object getInitialValue() {
        return initialValue;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(%s,%s)=>{", ARGUMENT_LEFT, ARGUMENT_RIGHT);
        js.append(this.js);
        js.append("}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        if (js == null || js.isEmpty()) {
            throw new WidgetConfigurationException("illegal statement");
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
    }
}
