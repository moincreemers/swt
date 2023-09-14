package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.SetParametersFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.HasURL;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Sets multiple parameters of the provided data source widget using the provided object")
public class SetQueryParametersStatement extends MethodStatement {
    private final HasURL hasURL;
    private final ValueStatement object;

    public SetQueryParametersStatement(HasURL hasURL, ValueStatement object) {
        this.hasURL = hasURL;
        this.object = object;
    }

    @Override
    public JsType getType() {
        return JsType.VOID;
    }

    @Override
    public List<JsParameter> getParameters() {
        return Statement.NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("%s('%s',%s);",
                JsWidgetModule.getQualifiedId(SetParametersFunction.class),
                hasURL.asWidget().getId(),
                ValueStatement.valueOf(toolkit, object, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertWidget("hasURL", hasURL);
        hasURL.asWidget().validate(toolkit);
        StatementUtil.assertRequiredAndReturnType("object", object, JsType.OBJECT);
        object.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(object);
    }
}
