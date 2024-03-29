package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.global.SetSessionValueFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.RequestUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.Arrays;
import java.util.List;

@Description("Sets the provided access token for the current session")
public class SetAccessTokenStatement extends MethodStatement {
    final ValueStatement accessToken;

    public SetAccessTokenStatement(ValueStatement accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public JsType getType() {
        return JsType.VOID;
    }

    @Override
    public List<JsParameter> getParameters() {
        return Arrays.asList(JsParameter.getInstance("value", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("%s('%s',%s);",
                JsGlobalModule.getQualifiedId(SetSessionValueFunction.class),
                RequestUtil.GLOBAL_ACCESS_TOKEN_KEY,
                ValueStatement.valueOf(toolkit, accessToken, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertRequiredAndReturnType("accessToken", accessToken, JsType.STRING);
        accessToken.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(accessToken);
    }
}
