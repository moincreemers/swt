package com.philips.dmis.swt.ui.toolkit.statement.predicate;

import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;

import java.util.Arrays;
import java.util.List;

/**
 * Predicate statements are boolean functions with a single constant argument.
 */
public abstract class PredicateStatement extends Statement {
    public static final String VALUE = "value";

    @Override
    public final JsType getType() {
        return JsType.BOOLEAN;
    }

    public abstract JsType getParameterType();

    public final List<JsParameter> getParameters() {
        return Arrays.asList(JsParameter.getInstance(VALUE, getParameterType()));
    }
}