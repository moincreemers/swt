package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

/**
 * Returns the ISO8601 Week number for the specified date.
 */
public class WeekOfYearFunction implements JsFunction {
    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return true;
    }

    @Override
    public boolean isPublic() {
        return true;
    }

    @Override
    public String getPublicName(String id) {
        return id;
    }

    @Override
    public JsType getType() {
        return JsType.NUMBER;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("(date)=>{");

        js.append("var dt=new Date(date.valueOf());");
        js.append("var day=(date.getDay()+6)%7;");
        js.append("dt.setDate(dt.getDate()-day+3);");
        js.append("var firstThursday=dt.valueOf();");
        js.append("dt.setMonth(0,1);");
        js.append("if (dt.getDay()!==4){");
        js.append("dt.setMonth(0,1+((4-dt.getDay())+7)%7);");
        js.append("};");
        js.append("return 1+Math.ceil((firstThursday-dt)/604800000);");

        js.append("}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("date", JsType.DATE));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
