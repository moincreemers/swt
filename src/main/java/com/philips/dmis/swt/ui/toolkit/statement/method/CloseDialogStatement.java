package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.EventHandlerFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.RaiseEventFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.HtmlDialog;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Closes an instance of the HtmlDialog widget")
public class CloseDialogStatement extends MethodStatement {
    final HtmlDialog dialog;
    final ValueStatement returnValue;

    public CloseDialogStatement(HtmlDialog dialog, ValueStatement returnValue) {
        this.dialog = dialog;
        this.returnValue = returnValue;
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
        js.append("document.getElementById('%s').returnValue=%s;",
                dialog.getId(),
                ValueStatement.valueOf(toolkit, returnValue, widget));
        js.append("document.getElementById('%s').close();", dialog.getId());
        js.append("%s('%s',%s);",
                JsWidgetModule.getQualifiedId(RaiseEventFunction.class),
                dialog.getId(),
                JsWidgetModule.getQualifiedId(EventHandlerFunction.OnCloseDialogEventHandlerFunction.class));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertWidget("dialog", dialog);
        dialog.validate(toolkit);
        StatementUtil.assertRequired("returnValue", returnValue);
        returnValue.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(returnValue);
    }
}
