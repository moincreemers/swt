package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ObjectValue extends ValueStatement {
    private final Map<ValueStatement, ValueStatement> map = new LinkedHashMap<>();

    public ObjectValue() {
    }

    public ObjectValue add(ValueStatement property, ValueStatement value) {
        map.put(property, value);
        return this;
    }

    @Override
    public JsType getType() {
        return JsType.OBJECT;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("{");
        int i = 0;
        for (ValueStatement property : map.keySet()) {
            if (i > 0) {
                js.append(",");
            }
            property.renderJs(toolkit, widget, js);
            js.append(":");
            map.get(property).renderJs(toolkit, widget, js);
            i++;
        }
        js.append("}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        for (Map.Entry<ValueStatement, ValueStatement> entry : map.entrySet()) {
            entry.getKey().validate(toolkit);
            entry.getValue().validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.addAll(map.keySet());
        statements.addAll(map.values());
    }
}
