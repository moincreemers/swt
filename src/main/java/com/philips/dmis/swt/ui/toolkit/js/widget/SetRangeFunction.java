package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.HasRange;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetRangeFunction implements JsFunction {
    public static final String ID = "setRange";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasRange<?>;
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
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,min,max)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("const elem=document.getElementById(id);");

        js.ifInArray("widgetType", WidgetType.DATE.name(), WidgetType.MONTH.name(),
                WidgetType.WEEK.name(), WidgetType.TIME.name(),
                WidgetType.DATETIME.name(), WidgetType.NUMBER.name(),
                WidgetType.RANGE.name()); // if
        js.append("if(min!=null){");
        js.append("elem.setAttribute('min',min);");
        js.append("};");
        js.append("if(max!=null){");
        js.append("elem.setAttribute('max',max);");
        js.append("};");
        js.append("};");// end if

        js.append("}"); // end function
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("min", JsType.STRING));
        parameters.add(JsParameter.getInstance("max", JsType.STRING));
    }
}
