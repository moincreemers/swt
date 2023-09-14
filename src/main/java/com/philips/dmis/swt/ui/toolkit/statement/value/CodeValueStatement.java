package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Returns the value of a Javascript expression")
public class CodeValueStatement extends ValueStatement {
    private final String js;
    private final JsType jsType;

    public CodeValueStatement(String js, JsType jsType) {
        this.js = js;
        this.jsType = jsType;
    }

    @Override
    public JsType getType() {
        return jsType;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append(this.js);
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        if (js == null || js.isEmpty()) {
            throw new WidgetConfigurationException("illegal statement");
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
    }
}
