package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Returns a string that is the concatenation of each element in the array separated by the provided value")
public class ArrayJoinValueStatement extends ValueStatement {
    private final ValueStatement array;
    private final ValueStatement separator;

    public ArrayJoinValueStatement(ValueStatement array, ValueStatement separator) {
        this.array = array;
        this.separator = separator;
    }

    @Override
    public JsType getType() {
        return JsType.ARRAY;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        array.renderJs(toolkit, widget, js);
        js.append(".join(");
        separator.renderJs(toolkit, widget, js);
        js.append(")");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        array.validate(toolkit);
        separator.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(array);
        statements.add(separator);
    }
}
