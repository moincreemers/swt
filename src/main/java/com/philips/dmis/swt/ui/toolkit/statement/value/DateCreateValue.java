package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class DateCreateValue extends ValueStatement {
    private final ValueStatement dateValue;
    private final ValueStatement year;
    private final ValueStatement monthIndex;
    private final ValueStatement day;
    private final ValueStatement hour;
    private final ValueStatement minute;
    private final ValueStatement second;
    private final ValueStatement milliSecond;

    public DateCreateValue(ValueStatement dateValue) {
        this.dateValue = dateValue;
        this.year = null;
        this.monthIndex = null;
        this.day = null;
        this.hour = null;
        this.minute = null;
        this.second = null;
        this.milliSecond = null;
    }

    public DateCreateValue(ValueStatement year, ValueStatement monthIndex) {
        this(year, monthIndex, null, null, null, null, null);
    }

    public DateCreateValue(ValueStatement year, ValueStatement monthIndex, ValueStatement day) {
        this(year, monthIndex, day, null, null, null, null);
    }

    public DateCreateValue(ValueStatement year, ValueStatement monthIndex, ValueStatement day, ValueStatement hour) {
        this(year, monthIndex, day, hour, null, null, null);
    }

    public DateCreateValue(ValueStatement year, ValueStatement monthIndex, ValueStatement day, ValueStatement hour, ValueStatement minute) {
        this(year, monthIndex, day, hour, minute, null, null);
    }

    public DateCreateValue(ValueStatement year, ValueStatement monthIndex, ValueStatement day, ValueStatement hour, ValueStatement minute, ValueStatement second) {
        this(year, monthIndex, day, hour, minute, second, null);
    }

    public DateCreateValue(ValueStatement year, ValueStatement monthIndex, ValueStatement day, ValueStatement hour, ValueStatement minute, ValueStatement second, ValueStatement milliSecond) {
        this.dateValue = null;
        this.year = year;
        this.monthIndex = monthIndex;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.milliSecond = milliSecond;
    }

    @Override
    public JsType getType() {
        return JsType.DATE;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("new Date(");
        if (dateValue != null) {
            dateValue.renderJs(toolkit, widget, js);
        } else {
            year.renderJs(toolkit, widget, js);
            js.append(",");
            monthIndex.renderJs(toolkit, widget, js);
            if (day != null) {
                js.append(",");
                day.renderJs(toolkit, widget, js);
                if (hour != null) {
                    js.append(",");
                    hour.renderJs(toolkit, widget, js);
                    if (minute != null) {
                        js.append(",");
                        minute.renderJs(toolkit, widget, js);
                        if (second != null) {
                            js.append(",");
                            second.renderJs(toolkit, widget, js);
                            if (milliSecond != null) {
                                js.append(",");
                                milliSecond.renderJs(toolkit, widget, js);
                            }
                        }
                    }
                }
            }
        }
        js.append(")");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        if (dateValue != null) {
            dateValue.validate(toolkit);
        } else {
            year.validate(toolkit);
            monthIndex.validate(toolkit);
            if (day != null) {
                day.validate(toolkit);
            }
            if (hour != null) {
                hour.validate(toolkit);
            }
            if (minute != null) {
                minute.validate(toolkit);
            }
            if (second != null) {
                second.validate(toolkit);
            }
            if (milliSecond != null) {
                milliSecond.validate(toolkit);
            }
        }
    }
}
