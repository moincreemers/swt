package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ContentType;
import com.philips.dmis.swt.ui.toolkit.dto.XhrResponse;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.ProcessResponseFunction;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceSupplier;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

// todo: why does this exist exactly?
//  we need the URL of the original query if we want to be able to use local cache
public class SendResponseStatement extends MethodStatement {
    private final DataSourceSupplier dataSourceSupplier;

    public SendResponseStatement(DataSourceSupplier dataSourceSupplier) {
        this.dataSourceSupplier = dataSourceSupplier;
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
        //xhrResponse
        js.append("var xhrResponse=%s;", DtoUtil.valueOf(new XhrResponse(
                200,
                "data://" + widget.getId(),
                new ContentType("application/json"),
                null)));
        js.append("%s('%s',xhrResponse);",
                JsWidgetModule.getQualifiedId(ProcessResponseFunction.class),
                dataSourceSupplier.asWidget().getId());
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertWidget("dataSourceSupplier", dataSourceSupplier);
        dataSourceSupplier.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
    }
}
