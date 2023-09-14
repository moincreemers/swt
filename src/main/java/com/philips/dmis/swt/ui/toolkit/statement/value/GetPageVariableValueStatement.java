package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.controller.GetCurrentPageFunction;
import com.philips.dmis.swt.ui.toolkit.js.controller.JsPageControllerModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.GetPageVariableFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Returns the value of a named page variable")
public class GetPageVariableValueStatement extends ValueStatement {
    private final ValueStatement name;

    public GetPageVariableValueStatement(String name) {
        this(new ConstantValueStatement(name));
    }

    public GetPageVariableValueStatement(ValueStatement name) {
        this.name = name;
    }

    @Override
    public JsType getType() {
        return JsType.ARRAY;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("%s(%s(),%s)",
                JsWidgetModule.getQualifiedId(GetPageVariableFunction.class),
                JsPageControllerModule.getQualifiedId(GetCurrentPageFunction.class),
                ValueStatement.valueOf(toolkit, name, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        name.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(name);
    }
}
