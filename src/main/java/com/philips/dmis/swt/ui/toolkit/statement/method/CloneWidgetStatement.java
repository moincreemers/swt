package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.CloneFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.ContainerWidget;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class CloneWidgetStatement extends MethodStatement {
    private final Widget template;
    private final ContainerWidget<?> target;
    private final ValueStatement dataKeyField;
    private final ValueStatement dataKey;

    public CloneWidgetStatement(Widget template, ContainerWidget<?> target, ValueStatement dataKey) {
        this(template, target, null, dataKey);
    }

    public CloneWidgetStatement(Widget template, ContainerWidget<?> target, ValueStatement dataKeyField, ValueStatement dataKey) {
        this.template = template;
        this.target = target;
        this.dataKeyField = dataKeyField;
        this.dataKey = dataKey;
    }

    @Override
    public JsType getType() {
        return JsType.VOID;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        String dataKeyFieldValue = dataKeyField != null ? ValueStatement.valueOf(toolkit, dataKeyField, widget) : "null";
        String dataKeyValue = ValueStatement.valueOf(toolkit, dataKey, widget);
        js.append("%s('%s','%s',%s,%s);",
                JsWidgetModule.getQualifiedId(CloneFunction.class),
                template.getId(),
                target.getId(),
                dataKeyFieldValue,
                dataKeyValue);
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        template.validate(toolkit);
        target.validate(toolkit);
        if (dataKeyField != null) {
            dataKeyField.validate(toolkit);
        }
        dataKey.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        if (dataKeyField != null) {
            statements.add(dataKeyField);
        }
        statements.add(dataKey);
    }
}
