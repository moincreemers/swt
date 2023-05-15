package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class WeekOfYearToDateFunction implements JsFunction {
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
        return JsType.DATE;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("(weekOfYear)=>{");
        //js.debug("console.log('WeekOfYearToDateFunction',weekOfYear);");
        js.append("if(weekOfYear==null||weekOfYear==undefined||weekOfYear.length==0){return new Date();};");
        js.append("const yearAndWeek=weekOfYear.split('-');");
        js.append("const year=parseInt(yearAndWeek[0]);");
        js.append("const weekNumber=parseInt(yearAndWeek[1].substring(1));");
        js.append("var ws=new Date(year,0,1+(weekNumber-1)*7);");
        js.append("var dayOfWeek=ws.getDay();");
        js.append("if(dayOfWeek<=4){");
        js.append("ws.setDate(ws.getDate()-ws.getDay()+1);");
        js.append("}else{");
        js.append("ws.setDate(ws.getDate()+8-ws.getDay());");
        js.append("};");
        js.append("return ws;");
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
