package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.GetParameterFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceSupplier;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class GetDataSourceParameterValueStatement extends ValueStatement {
    private final DataSourceSupplier dataSourceSupplier;
    private final ValueStatement nameStatement;

    public GetDataSourceParameterValueStatement(DataSourceSupplier dataSourceSupplier, ValueStatement nameStatement) {
        this.dataSourceSupplier = dataSourceSupplier;
        this.nameStatement = nameStatement;
    }

    @Override
    public JsType getType() {
        return JsType.OBJECT;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("%s('%s','%s');",
                JsWidgetModule.getQualifiedId(GetParameterFunction.class),
                dataSourceSupplier.asWidget(),
                ValueStatement.valueOf(toolkit, nameStatement, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        dataSourceSupplier.validate(toolkit);
        nameStatement.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(nameStatement);
    }
}
