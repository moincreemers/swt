package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.AppendListItemFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.HasListItems;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Description("Adds a list item to a list")
public class AppendItemStatement extends MethodStatement {
    final HasListItems hasListItems;
    final List<ValueStatement> values = new ArrayList<>();

    public AppendItemStatement(HasListItems hasListItems, ValueStatement... values) {
        this.hasListItems = hasListItems;
        this.values.addAll(Arrays.asList(values));
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
        for (ValueStatement value : values) {
            js.append("%s(document.getElementById('%s'),%s,'');",
                    JsWidgetModule.getQualifiedId(AppendListItemFunction.class),
                    hasListItems.asWidget().getId(),
                    ValueStatement.valueOf(toolkit, value, hasListItems.asWidget()));
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertWidget("hasListItems", hasListItems);
        hasListItems.asWidget().validate(toolkit);
        StatementUtil.assertSize("values", values, 1);
        for (ValueStatement value : values) {
            StatementUtil.assertReturnType("values", value, JsType.STRING);
            value.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.addAll(values);
    }
}
