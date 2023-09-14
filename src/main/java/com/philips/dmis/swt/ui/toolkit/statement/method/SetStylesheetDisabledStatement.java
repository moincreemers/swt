package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Disables or enables a stylesheet")
public class SetStylesheetDisabledStatement extends MethodStatement {
    private final ValueStatement href;
    private final ValueStatement disabled;

    public SetStylesheetDisabledStatement(ValueStatement href, ValueStatement disabled) {
        this.href = href;
        this.disabled = disabled;
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
        js.append("for(const i in document.styleSheets){");
        //js.append("console.log('SetStylesheetDisabledStatement',document.styleSheets[i].href);");
        js.append("const href=document.styleSheets[i].href;");
        js.append("if(href==undefined||href==null||href==''){continue;}");
        js.append("if(href==document.location.origin+'/'+%s){",
                ValueStatement.valueOf(toolkit, href, widget));
        js.append("document.styleSheets[i].disabled=%s;",
                ValueStatement.valueOf(toolkit, disabled, widget));
        js.append("break;");
        js.append("};");
        js.append("};");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertRequiredAndReturnType("href", href, JsType.STRING);
        href.validate(toolkit);
        StatementUtil.assertRequiredAndReturnType("disabled", disabled, JsType.BOOLEAN);
        disabled.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(href);
        statements.add(disabled);
    }
}
