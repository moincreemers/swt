package com.philips.dmis.swt.ui.toolkit.statement.dom;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class GetElementByIdStatement extends DOMSelectorStatement {
    private final ValueStatement id;

    public GetElementByIdStatement(ValueStatement id) {
        this.id = id;
    }

    @Override
    public List<JsParameter> getParameters() {
        return List.of(JsParameter.getInstance("id", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("Array.from(document.getElementsById(%s))", ValueStatement.valueOf(toolkit, id, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        id.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(id);
    }
}
