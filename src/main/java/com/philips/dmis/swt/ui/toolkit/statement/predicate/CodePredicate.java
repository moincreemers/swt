package com.philips.dmis.swt.ui.toolkit.statement.predicate;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

/**
 * Constructs a predicate using Javascript.
 * <p>
 * The code:
 * <ul>
 * <li>MAY include any number of statements</li>
 * <li>MUST return a boolean value</li>
 * </ul>
 */
public class CodePredicate extends PredicateStatement {
    private final String js;

    public CodePredicate(String js) {
        this.js = js;
    }

    @Override
    public JsType getParameterType() {
        return JsType.OBJECT;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("()=>{");
        js.append(this.js);
        js.append("}");
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
        if (js.indexOf("return") == -1) {
            throw new WidgetConfigurationException("missing return statement");
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
    }
}
