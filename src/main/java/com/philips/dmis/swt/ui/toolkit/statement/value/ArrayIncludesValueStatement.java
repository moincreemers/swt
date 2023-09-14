package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Returns true if the array contains the specified value")
public class ArrayIncludesValueStatement extends ValueStatement {
    private final ValueStatement array;
    private final ValueStatement value;

    public ArrayIncludesValueStatement(ValueStatement array, ValueStatement value) {
        this.array = array;
        this.value = value;
    }

    @Override
    public JsType getType() {
        return JsType.BOOLEAN;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        array.renderJs(toolkit, widget, js);
        js.append(".includes(");
        value.renderJs(toolkit, widget, js);
        js.append(")");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        array.validate(toolkit);
        value.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(array);
        statements.add(value);
    }
}
