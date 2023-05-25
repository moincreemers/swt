package com.philips.dmis.swt.ui.toolkit.statement.mapper;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.method.CallStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class Call extends MapStatement {
    final CallStatement callStatement;

    public Call(CallStatement callStatement) {
        super(callStatement.getType());
        this.callStatement = callStatement;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        List<JsParameter> parameters = callStatement.getParameters();
        if (parameters.isEmpty()) {
            throw new JsRenderException("missing parameter");
        }
        js.append("(%s,%s,%s,", ARGUMENT_SERVICE_RESPONSE, ARGUMENT_TARGET, ARGUMENT_OBJECT);
        int i = 0;
        for (JsParameter parameter : parameters) {
            if (i > 0) {
                js.append(",");
            }
            js.append(parameter.getName());
            i++;
        }
        js.append(")=>{");
        callStatement.renderJs(toolkit, widget, js);
        js.append("}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        callStatement.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(callStatement);
    }
}
