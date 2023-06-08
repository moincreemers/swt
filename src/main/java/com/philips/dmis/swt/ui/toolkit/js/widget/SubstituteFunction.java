package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SubstituteFunction implements JsFunction {
    public static final String ID = "substitute";

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
        return JsType.STRING;
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("eventContext", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,eventContext)=>{");
        js.trace(this);

        js.append("if(eventContext==undefined||eventContext==null||eventContext.dataKey==null||eventContext.dataKey==''){return id;};");

        String substitutionsVar = JsWidgetModule.getQualifiedId(SubstitutionsVariable.class);
        js.append("if(%s.hasOwnProperty(eventContext.dataKey)){", substitutionsVar);
        js.append("if(%s[eventContext.dataKey].hasOwnProperty(id)){", substitutionsVar);
        js.append("return %s[eventContext.dataKey][id];", substitutionsVar);
        js.append("};");
        js.append("};");

        js.append("return id;");

        js.append("}"); // end function
    }
}
