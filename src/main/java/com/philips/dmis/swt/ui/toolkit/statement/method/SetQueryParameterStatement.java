package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.pages.JsPagesModule;
import com.philips.dmis.swt.ui.toolkit.js.pages.SetParameterFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceSupplier;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class SetQueryParameterStatement extends MethodStatement {
    private final DataSourceSupplier dataSourceSupplier;
    private final ValueStatement nameStatement;
    private final ValueStatement valueStatement;

    public SetQueryParameterStatement(DataSourceSupplier dataSourceSupplier, ValueStatement nameStatement, ValueStatement valueStatement) {
        this.dataSourceSupplier = dataSourceSupplier;
        this.nameStatement = nameStatement;
        this.valueStatement = valueStatement;
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
        js.append("%s(%s,%s);",
                JsPagesModule.getQualifiedId(dataSourceSupplier.asWidget(),
                        SetParameterFunction.class),
                ValueStatement.valueOf(toolkit, nameStatement, widget),
                ValueStatement.valueOf(toolkit, valueStatement, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        dataSourceSupplier.validate(toolkit);
        nameStatement.validate(toolkit);
        valueStatement.validate(toolkit);
    }
}
