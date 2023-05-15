package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsFunction;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.method.CallStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Code;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CallValue extends ValueStatement {
    private Code code;
    private String name;
    private final List<ValueStatement> parameterValues = new ArrayList<>();

    public CallValue(Code code, String name, ValueStatement... parameterValues) {
        this.code = code;
        this.name = name;
        this.parameterValues.addAll(Arrays.asList(parameterValues));
    }

    public CallValue clearParameterValues() {
        parameterValues.clear();
        return this;
    }

    public CallValue addParameterValue(ValueStatement parameterValue) {
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
        js.append("(function(){");

        CallStatement callStatement = new CallStatement(code, name,
                parameterValues.toArray(new ValueStatement[0]));
        callStatement.renderJs(toolkit, widget, js);

        js.append("})()");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.addAll(parameterValues);
    }
}
