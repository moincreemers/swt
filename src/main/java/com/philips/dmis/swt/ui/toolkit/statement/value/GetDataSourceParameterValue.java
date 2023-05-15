package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.pages.GetParameterFunction;
import com.philips.dmis.swt.ui.toolkit.js.pages.JsPagesModule;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceSupplier;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class GetDataSourceParameterValue extends ValueStatement {
    private final DataSourceSupplier dataSourceSupplier;
    private final ValueStatement nameStatement;

    public GetDataSourceParameterValue(DataSourceSupplier dataSourceSupplier, ValueStatement nameStatement) {
        this.dataSourceSupplier = dataSourceSupplier;
        this.nameStatement = nameStatement;
    }

    @Override
    public JsType getType() {
        return JsType.OBJECT;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("%s('%s');",
                JsPagesModule.getQualifiedId(dataSourceSupplier.asWidget(),
                        GetParameterFunction.class),
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
