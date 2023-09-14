package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.GetFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.SubstituteFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.HasValueType;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Returns the value of the specified widget")
public class GetValueStatement extends ValueStatement {
    private final HasValueType<?> hasValueType;

    public GetValueStatement(HasValueType<?> hasValueType) {
        this.hasValueType = hasValueType;
    }

    @Override
    public JsType getType() {
        return hasValueType.getReturnType();
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("%s(%s('%s',eventContext))",
                JsWidgetModule.getQualifiedId(GetFunction.class),
                JsWidgetModule.getQualifiedId(SubstituteFunction.class),
                hasValueType.asWidget().getId());
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        hasValueType.asWidget().validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
    }
}
