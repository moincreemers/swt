package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.GetSearchString;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.global.SearchToObjectFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Returns the query string arguments as an object")
public class GetQueryStringAsObjectValueStatement extends ValueStatement {
    private final ValueStatement url;

    public GetQueryStringAsObjectValueStatement(ValueStatement url) {
        this.url = url;
    }

    @Override
    public JsType getType() {
        return JsType.STRING;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("%s(%s(%s))",
                JsGlobalModule.getQualifiedId(SearchToObjectFunction.class),
                JsGlobalModule.getQualifiedId(GetSearchString.class),
                ValueStatement.valueOf(toolkit, url, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        url.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(url);
    }
}
