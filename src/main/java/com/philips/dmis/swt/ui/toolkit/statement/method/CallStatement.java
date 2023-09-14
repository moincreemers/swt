package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsFunction;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Code;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Description("Invokes a method declared in a Code block")
public class CallStatement extends MethodStatement {
    private Code code;
    private String name;
    private final List<ValueStatement> parameterValues = new ArrayList<>();

    public CallStatement(Code code, String name, ValueStatement... parameterValues) {
        this.code = code;
        this.name = name;
        this.parameterValues.addAll(Arrays.asList(parameterValues));
    }

    public CallStatement clearParameterValues() {
        parameterValues.clear();
        return this;
    }

    public CallStatement addParameterValue(ValueStatement parameterValue) {
        parameterValues.add(parameterValue);
        return this;
    }

    @Override
    public JsType getType() {
        return code.getFunction(name).getType();
    }

    @Override
    public List<JsParameter> getParameters() {
        JsFunction jsFunction = code.getFunction(name);
        List<JsParameter> parameters = new ArrayList<>();
        jsFunction.getParameters(parameters);
        return parameters;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        JsFunction jsFunction = code.getFunction(name);
        String publicName = jsFunction.getPublicName("");
        js.append("return %s.%s(", code.getModuleName(), publicName);

        // note: the call may send any number of parameters independent of
        //  the number of parameters the function declares
        int i = 0;
        if (parameterValues.isEmpty()) {
            List<JsParameter> parameters = getParameters();
            for (JsParameter jsParameter : parameters) {
                if (i > 0) {
                    js.append(",");
                }
                js.append(jsParameter.getName());
                i++;
            }
        } else {
            for (ValueStatement parameterValue : parameterValues) {
                if (i > 0) {
                    js.append(",");
                }
                parameterValue.renderJs(toolkit, code, js);
                i++;
            }
        }
        js.append(");");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;

        StatementUtil.assertWidget("code", code);
        code.validate(toolkit);

        for (ValueStatement valueStatement : parameterValues) {
            valueStatement.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.addAll(parameterValues);
    }
}
