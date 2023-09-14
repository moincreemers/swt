package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Returns true if the provided values are equal")
public class IsValueStatement extends ValueStatement {
    private final ValueStatement left;
    private final ValueStatement right;

    public IsValueStatement(ValueStatement left, ValueStatement right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public JsType getType() {
        return JsType.BOOLEAN;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(");
        left.renderJs(toolkit, widget, js);
        js.append("==");
        right.renderJs(toolkit, widget, js);
        js.append(")");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        left.validate(toolkit);
        right.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(left);
        statements.add(right);
    }
}
