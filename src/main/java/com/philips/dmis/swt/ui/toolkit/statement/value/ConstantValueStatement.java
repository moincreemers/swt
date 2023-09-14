package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ConstantValueStatement extends ValueStatement {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public final Object value;
    public final JsType jsType;

    public ConstantValueStatement(int value) {
        this.value = value;
        this.jsType = JsType.NUMBER;
    }

    public ConstantValueStatement(long value) {
        this.value = value;
        this.jsType = JsType.NUMBER;
    }

    public ConstantValueStatement(double value) {
        this.value = value;
        this.jsType = JsType.NUMBER;
    }

    public ConstantValueStatement(boolean value) {
        this.value = value;
        this.jsType = JsType.BOOLEAN;
    }

    public ConstantValueStatement(String value) {
        this(value, false);
    }

    public ConstantValueStatement(String value, boolean symbol) {
        this.value = value;
        this.jsType = symbol ? JsType.SYMBOL : JsType.STRING;
    }

    public ConstantValueStatement(int hour, int minute) {
        this(1970, Calendar.JANUARY, 1, hour, minute, 0, 0);
    }

    public ConstantValueStatement(int year, int month, int dayOfMonth) {
        this(year, month, dayOfMonth, 0, 0, 0, 0);
    }

    public ConstantValueStatement(int year, int month, int dayOfMonth, int hour, int minute, int second, int milliSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, dayOfMonth);
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, milliSecond);
        this.value = calendar.getTime();
        this.jsType = JsType.DATE;
    }

    public ConstantValueStatement(Object[] array) {
        this.value = array;
        this.jsType = JsType.ARRAY;
    }

    public ConstantValueStatement(Iterable<?> iterable) {
        this.value = Arrays.asList(iterable).toArray();
        this.jsType = JsType.ARRAY;
    }

    public ConstantValueStatement(Object value, JsType jsType) {
        this.value = value;
        this.jsType = jsType;
    }

    @Override
    public JsType getType() {
        return jsType;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        switch (jsType) {
            case STRING:
                js.append("'%s'", value);
                break;
            case NUMBER:
                js.append(value.toString());
                break;
            case BOOLEAN:
                js.append((boolean) value ? "true" : "false");
                break;

            case OBJECT:
                try {
                    js.append(OBJECT_MAPPER.writeValueAsString(value));
                } catch (JsonProcessingException e) {
                    throw new JsRenderException(e);
                }
                break;
            case ARRAY:
                js.append("[");
                Object[] array = (Object[]) value;
                for (int i = 0; i < array.length; i++) {
                    Object v = array[i];
                    if (i > 0) {
                        js.append(",");
                    }
                    if (v instanceof String) {
                        js.append("'%s'", v);
                    } else {
                        js.append(v.toString());
                    }
                }
                js.append("]");
                break;
            case NULL:
                js.append("null");
                break;
            case REGEX:
                js.append("");
                break;
            case VOID:
                js.append("void");
                break;

            case DATE:
                js.append("new Date(%d)", value);
                break;

//            case BIGINT:
//            case SYMBOL:
//            case FUNCTION:
//            case UNDEFINED:
            default:
                js.append("%s", value);
                break;
        }

    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
    }

    @Override
    public void getReferences(List<Statement> statements) {
    }
}
