package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Converts an object into a JSON string")
public class GetJSONValueStatement extends ValueStatement {
    private final ValueStatement valueStatement;

    public GetJSONValueStatement(ValueStatement valueStatement) {
        this.valueStatement = valueStatement;
    }

    @Override
    public JsType getType() {
        return JsType.STRING;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("JSON.stringify(");
        valueStatement.renderJs(toolkit, widget, js);
        js.append(",null,4)");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        valueStatement.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(valueStatement);
    }
}
