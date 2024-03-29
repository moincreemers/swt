package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.RemoveCloneFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Removes a cloned instance that was created using the provided template widget that is identified by the provided data key value")
public class RemoveCloneStatement extends MethodStatement {
    private final Widget template;
    private final ValueStatement dataKey;

    public RemoveCloneStatement(Widget template, ValueStatement dataKey) {
        this.template = template;
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
        js.append("%s('%s',%s);",
                JsWidgetModule.getQualifiedId(RemoveCloneFunction.class),
                template.getId(),
                ValueStatement.valueOf(toolkit, dataKey, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertWidget("template", template);
        template.validate(toolkit);
        StatementUtil.assertRequiredAndReturnType("dataKey", dataKey, JsType.STRING);
        dataKey.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(dataKey);
    }
}
