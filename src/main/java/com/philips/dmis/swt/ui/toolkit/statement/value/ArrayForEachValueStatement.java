package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.method.MethodStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Performs the provided method statement for each of the elements in the array")
public class ArrayForEachValueStatement extends ValueStatement {
    private final ValueStatement array;
    private final MethodStatement method;

    public ArrayForEachValueStatement(ValueStatement array, MethodStatement method) {
        this.array = array;
        this.method = method;
    }

    @Override
    public JsType getType() {
        return JsType.ARRAY;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        array.renderJs(toolkit, widget, js);
        js.append(".forEach((%s)=>{", method.getParameters().get(0).getName());
        method.renderJs(toolkit, widget, js);
        js.append("})");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        array.validate(toolkit);
        method.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(array);
        statements.add(method);
    }
}
