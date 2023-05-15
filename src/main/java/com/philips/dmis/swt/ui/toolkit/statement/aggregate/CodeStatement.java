package com.philips.dmis.swt.ui.toolkit.statement.aggregate;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class CodeStatement extends AggregateStatement {
    private final String js;

    public CodeStatement(String js) {
        super(JsType.NUMBER);
        this.js = js;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(data)=>{");
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
