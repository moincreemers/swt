package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.state.JsStateModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.ResetFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.SubstituteFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.HtmlForm;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Resets the HtmlForm widget")
public class ResetStatement extends MethodStatement {
    private final HtmlForm widget;

    public ResetStatement(HtmlForm widget) {
        this.widget = widget;
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
        js.append("%s(%s('%s',eventContext),'%s');",
                JsWidgetModule.getQualifiedId(ResetFunction.class),
                JsWidgetModule.getQualifiedId(SubstituteFunction.class),
                this.widget.getId(),
                JsStateModule.REASON_USER);
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertWidget("widget", widget);
        widget.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
    }
}
