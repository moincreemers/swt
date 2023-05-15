package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.pages.DeleteDataFunction;
import com.philips.dmis.swt.ui.toolkit.js.pages.JsPagesModule;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.DataBoundWidget;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class DeleteDataStatement extends MethodStatement {
    private final DataBoundWidget<?> dataBoundWidget;
    private final ValueStatement selection;

    public DeleteDataStatement(DataBoundWidget<?> dataBoundWidget, ValueStatement selection) {
        this.dataBoundWidget = dataBoundWidget;
        this.selection = selection;
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
                JsPagesModule.getQualifiedId(dataBoundWidget, DeleteDataFunction.class),
                ValueStatement.valueOf(toolkit, selection, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        dataBoundWidget.validate(toolkit);
        selection.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(selection);
    }
}