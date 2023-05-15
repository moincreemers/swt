package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.pages.AddDataFunction;
import com.philips.dmis.swt.ui.toolkit.js.pages.JsPagesModule;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ConstantValue;
import com.philips.dmis.swt.ui.toolkit.widgets.DataBoundWidget;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class AddDataStatement extends MethodStatement {
    private final DataBoundWidget<?> dataBoundWidget;
    private final ValueStatement numberOfRecords;

    public AddDataStatement(DataBoundWidget<?> dataBoundWidget) {
        this(dataBoundWidget, new ConstantValue("1"));
    }

    public AddDataStatement(DataBoundWidget<?> dataBoundWidget, ValueStatement numberOfRecords) {
        this.dataBoundWidget = dataBoundWidget;
        this.numberOfRecords = numberOfRecords;
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
        js.append("%s(%s);",
                JsPagesModule.getQualifiedId(dataBoundWidget, AddDataFunction.class),
                ValueStatement.valueOf(toolkit, numberOfRecords, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        dataBoundWidget.validate(toolkit);
        numberOfRecords.validate(toolkit);
    }
}
