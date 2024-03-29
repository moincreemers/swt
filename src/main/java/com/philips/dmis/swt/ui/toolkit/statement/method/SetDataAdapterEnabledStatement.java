package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.SetDataAdapterEnabledFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.SubstituteFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Enables or disables the provided data adapter on the provided data source/consumer widget")
public class SetDataAdapterEnabledStatement extends MethodStatement {
    private final Widget widget;
    private final DataAdapter dataAdapter;
    private final ValueStatement enabled;
    private final boolean invert;

    public SetDataAdapterEnabledStatement(Widget widget, DataAdapter dataAdapter, ValueStatement enabled, boolean invert) {
        this.widget = widget;
        this.dataAdapter = dataAdapter;
        this.enabled = enabled;
        this.invert = invert;

        if (enabled.getType() != JsType.BOOLEAN) {
            throw new IllegalArgumentException("expected boolean value 'enabled'");
        }
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
        js.append("%s(%s('%s',eventContext),'%s',(%s==%s));",
                JsWidgetModule.getQualifiedId(SetDataAdapterEnabledFunction.class),
                JsWidgetModule.getQualifiedId(SubstituteFunction.class),
                this.widget.getId(),
                dataAdapter.getId(),
                ValueStatement.valueOf(toolkit, enabled, widget),
                invert ? "false" : "true");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertWidget("widget", widget);
        widget.validate(toolkit);
        StatementUtil.assertDataAdapter("dataAdapter", dataAdapter);
        dataAdapter.validate(toolkit);
        enabled.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(enabled);
    }
}
