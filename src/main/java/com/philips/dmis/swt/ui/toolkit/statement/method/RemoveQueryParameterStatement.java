package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.RemoveParameterFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.SubstituteFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceSupplier;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Removes the provided parameter from the provided data source widget")
public class RemoveQueryParameterStatement extends MethodStatement {
    private final DataSourceSupplier dataSourceSupplier;
    private final ValueStatement name;

    public RemoveQueryParameterStatement(DataSourceSupplier dataSourceSupplier, ValueStatement name) {
        this.dataSourceSupplier = dataSourceSupplier;
        this.name = name;
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
        js.append("%s(%s('%s',eventContext),%s);",
                JsWidgetModule.getQualifiedId(RemoveParameterFunction.class),
                JsWidgetModule.getQualifiedId(SubstituteFunction.class),
                dataSourceSupplier.asWidget().getId(),
                ValueStatement.valueOf(toolkit, name, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertWidget("dataSourceSupplier", dataSourceSupplier);
        dataSourceSupplier.validate(toolkit);
        StatementUtil.assertRequiredAndReturnType("name", name, JsType.STRING);
        name.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(name);
    }
}
