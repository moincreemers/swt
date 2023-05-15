package com.philips.dmis.swt.ui.toolkit.statement.mapper;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.Arrays;
import java.util.List;

/**
 * A map statement is a statement that receives a value of a certain type
 * and returns a value of another type.
 */
public abstract class MapStatement extends Statement {
    public static final String ARGUMENT_VALUE = "value";

    public static String valueOf(Toolkit toolkit, MapStatement mapStatement, Widget widget) {
        if (mapStatement == null) {
            return String.format("(%s)=>{return null;}", ARGUMENT_VALUE);
        }
        JsWriter js = new JsWriter();
        mapStatement.renderJs(toolkit, widget, js);
        return js.toString();
    }

    private final JsType jsType;

    public MapStatement(JsType jsType) {
        this.jsType = jsType;
    }

    @Override
    public final JsType getType() {
        return jsType;
    }

    public final List<JsParameter> getParameters() {
        return Arrays.asList(JsParameter.getInstance(ARGUMENT_VALUE, JsType.OBJECT));
    }
}
