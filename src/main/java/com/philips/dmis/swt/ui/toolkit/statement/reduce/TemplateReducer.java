package com.philips.dmis.swt.ui.toolkit.statement.reduce;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class TemplateReducer extends ReducerStatement {
    public TemplateReducer() {
        super(JsType.NUMBER);
    }

    @Override
    public Object getInitialValue() {
        return 0;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(%s,%s)=>{", ARGUMENT_LEFT, ARGUMENT_RIGHT);
        js.append("return 0;");
        js.append("}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }

    @Override
    public void getReferences(List<Statement> statements) {
    }
}
