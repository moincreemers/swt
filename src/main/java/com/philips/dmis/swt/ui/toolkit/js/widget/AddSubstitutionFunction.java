package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class AddSubstitutionFunction implements JsFunction {
    public static final String ID = "addSubstitution";

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
        return JsType.VOID;
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("dataKey", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("widgetId", JsType.STRING));
        parameters.add(JsParameter.getInstance("slaveId", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(dataKey,widgetId,slaveId)=>{");
        js.trace(this);

        String substitutionsVar = JsWidgetModule.getQualifiedId(SubstitutionsVariable.class);
        js.append("if(!%s.hasOwnProperty(dataKey)){", substitutionsVar);
        js.append("%s[dataKey]={};", substitutionsVar);
        js.append("};");
        js.append("%s[dataKey][widgetId]=slaveId;", substitutionsVar);

        js.append("}"); // end function
    }
}
