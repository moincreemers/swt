package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArrayConcatValue extends ValueStatement {
    private List<ValueStatement> arrays = new ArrayList<>();

    public ArrayConcatValue(ValueStatement... arrays) {
        this.arrays.addAll(Arrays.asList(arrays));
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
        Iterator<ValueStatement> iterator = arrays.iterator();
        if (iterator.hasNext()) {
            iterator.next().renderJs(toolkit, widget, js);
        }
        js.append(".concat(");
        int i = 0;
        while (iterator.hasNext()) {
            if (i > 0) {
                js.append(",");
            }
            iterator.next().renderJs(toolkit, widget, js);
            i++;
        }
        js.append(")");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        for (ValueStatement array : arrays) {
            array.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.addAll(arrays);
    }
}
