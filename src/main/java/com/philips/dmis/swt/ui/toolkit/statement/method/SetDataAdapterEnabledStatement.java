package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.pages.JsPagesModule;
import com.philips.dmis.swt.ui.toolkit.js.pages.SetDataAdapterEnabledFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class SetDataAdapterEnabledStatement extends MethodStatement {
    private final Widget targetWidget;
    private final DataAdapter dataAdapter;
    private final ValueStatement enabled;

    public SetDataAdapterEnabledStatement(Widget targetWidget, DataAdapter dataAdapter, ValueStatement enabled) {
        this.targetWidget = targetWidget;
        this.dataAdapter = dataAdapter;
        this.enabled = enabled;

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
        js.append("%s('%s',%s);",
                JsPagesModule.getQualifiedId(targetWidget, SetDataAdapterEnabledFunction.class),
                dataAdapter.getId(),
                ValueStatement.valueOf(toolkit, enabled, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        targetWidget.validate(toolkit);
        dataAdapter.validate(toolkit);
        enabled.validate(toolkit);
    }
}
