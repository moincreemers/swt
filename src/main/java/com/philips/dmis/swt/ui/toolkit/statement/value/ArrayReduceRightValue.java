package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class ArrayReduceRightValue extends ValueStatement {
    private final ValueStatement array;
    private final ValueStatement reducer;
    private final ValueStatement initialValue;

    public ArrayReduceRightValue(ValueStatement array, ValueStatement reducer) throws WidgetConfigurationException {
        this(array, reducer, null);
    }

    public ArrayReduceRightValue(ValueStatement array, ValueStatement reducer, ValueStatement initialValue) throws WidgetConfigurationException {
        this.array = array;
        this.reducer = reducer;
        this.initialValue = initialValue;
        if (reducer.getParameters().size() < 2) {
            throw new WidgetConfigurationException("reducer requires 2 parameters");
        }
    }

    @Override
    public JsType getType() {
        return JsType.ARRAY;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        array.renderJs(toolkit, widget, js);
        js.append(".reduceRight((%s,%s)=>{", reducer.getParameters().get(0).getName(),
                reducer.getParameters().get(1).getName());
        reducer.renderJs(toolkit, widget, js);
        js.append("}");
        if (initialValue != null) {
            js.append(",");
            initialValue.renderJs(toolkit, widget, js);
        }
        js.append(")");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        array.validate(toolkit);
        reducer.validate(toolkit);
        if (initialValue != null) {
            initialValue.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(array);
        statements.add(reducer);
        statements.add(initialValue);
    }
}
