package com.philips.dmis.swt.ui.toolkit.statement.predicate;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotAndPredicate extends PredicateStatement {
    private final List<PredicateStatement> predicates = new ArrayList<>();

    public NotAndPredicate(PredicateStatement... predicates) {
        this.predicates.addAll(Arrays.asList(predicates));
    }

    @Override
    public JsType getParameterType() {
        return JsType.VOID;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("()=>{return !(");
        int i = 0;
        for (PredicateStatement predicate : predicates) {
            if (i > 0) {
                js.append("&&");
            }
            predicate.renderJs(toolkit, widget, js);
            i++;
        }
        js.append(");}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        for (PredicateStatement predicate : predicates) {
            predicate.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.addAll(predicates);
    }
}
