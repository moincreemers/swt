package com.philips.dmis.swt.ui.toolkit.statement.reduce;

import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;

import java.util.Arrays;
import java.util.List;

/**
 * A reducer is a statements that receives 2 arguments and outputs 1 value.
 */
public abstract class ReducerStatement extends Statement {
    public static final String ARGUMENT_LEFT = "a";
    public static final String ARGUMENT_RIGHT = "b";

    private final JsType jsType;

    public ReducerStatement(JsType jsType) {
        this.jsType = jsType;
    }

    public abstract Object getInitialValue();

    @Override
    public final JsType getType() {
        return jsType;
    }

    public final List<JsParameter> getParameters() {
        return Arrays.asList(
                JsParameter.getInstance(ARGUMENT_LEFT, jsType),
                JsParameter.getInstance(ARGUMENT_RIGHT, jsType));
    }
}
