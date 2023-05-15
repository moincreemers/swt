package com.philips.dmis.swt.ui.toolkit.statement.aggregate;

import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;

import java.util.Arrays;
import java.util.List;

/**
 * An aggregate is a statement that receives 1 argument and outputs 1 value of the same type.
 */
public abstract class AggregateStatement extends Statement {
    public static final String ARGUMENT_DATA = "data";

    private final JsType jsType;

    public AggregateStatement(JsType jsType) {
        this.jsType = jsType;
    }

    @Override
    public final JsType getType() {
        return jsType;
    }

    public final List<JsParameter> getParameters() {
        return Arrays.asList(JsParameter.getInstance(ARGUMENT_DATA, JsType.ARRAY));
    }
}
