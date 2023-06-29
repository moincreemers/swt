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

public class RemoveClassNameStatement extends DOMStatement {
    private final DOMSelectorStatement nodes;
    private final ValueStatement className;

    public RemoveClassNameStatement(DOMSelectorStatement nodes, ValueStatement className) {
        this.nodes = nodes;
        this.className = className;
    }

    @Override
    public JsType getType() {
        return JsType.VOID;
    }

    @Override
    public List<JsParameter> getParameters() {
        return List.of(JsParameter.getInstance("nodes", JsType.ARRAY),
                JsParameter.getInstance("className", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        nodes.renderJs(toolkit, widget, js);
        js.append(".forEach(");
        js.append("(e)=>{");
        js.append("e.classList.remove(%s);", ValueStatement.valueOf(toolkit, className, widget));
        js.append("}");
        js.append(");");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        className.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(className);
    }
}
