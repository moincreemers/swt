package com.philips.dmis.swt.ui.toolkit.statement.predicate;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

public class IsNullPredicate extends PredicateStatement {
    @Override
    public JsType getParameterType() {
        return JsType.BOOLEAN;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(value)=>{return (value==null);}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
    }
}
