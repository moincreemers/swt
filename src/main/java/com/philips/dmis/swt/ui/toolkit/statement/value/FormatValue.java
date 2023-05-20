package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.FormatStringFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class FormatValue extends ValueStatement {
    private final ValueStatement format;
    private final ValueStatement object;

    public FormatValue(ValueStatement format) {
        this(format, null);
    }

    public FormatValue(ValueStatement format, ValueStatement object) {
        this.format = format;
        this.object = object;
    }

    @Override
    public JsType getType() {
        return JsType.STRING;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("%s(%s,%s);",
                JsGlobalModule.getQualifiedId(FormatStringFunction.class),
                ValueStatement.valueOf(toolkit, format, widget),
                ValueStatement.valueOf(toolkit, object, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        format.validate(toolkit);
        if (object != null) {
            object.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(format);
        if (object != null) {
            statements.add(object);
        }
    }
}
