package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.global.WeekOfYearToDateFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class DateFromWeekOfYearValue extends ValueStatement {
    private final ValueStatement weekOfYear;

    public DateFromWeekOfYearValue(ValueStatement weekOfYear) {
        this.weekOfYear = weekOfYear;
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
        js.append("%s(", JsGlobalModule.getQualifiedId(WeekOfYearToDateFunction.class));
        weekOfYear.renderJs(toolkit, widget, js);
        js.append(")");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        weekOfYear.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(weekOfYear);
    }
}
