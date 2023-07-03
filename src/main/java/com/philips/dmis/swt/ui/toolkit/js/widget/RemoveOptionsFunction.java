package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.HasOptions;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RemoveOptionsFunction implements JsFunction {
    public static final String ID = "removeOptions";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasOptions;
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
        dependencies.add(GetElementFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("dataSourceId", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,dataSourceId)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        //js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("const element=document.getElementById(id);");

        js.ifInArray("widgetType", WidgetType.MULTIPLE_CHOICE.name(),
                WidgetType.SINGLE_CHOICE.name()); // if
        js.append("if(dataSourceId==undefined||dataSourceId==null||dataSourceId==''){");
        js.append("element.textContent='';");
        js.append("return;");
        js.append("};");
        js.append("const nlTemp=element.querySelectorAll('[tk-source=\\'__temp__\\']');");
        js.append("nlTemp.forEach((c)=>element.removeChild(c));");
        js.append("const nl=element.querySelectorAll('[tk-source=\\''+dataSourceId+'\\']');");
        js.append("nl.forEach((c)=>element.removeChild(c));");

        js.append("}else{");
        js.append("if(dataSourceId==undefined||dataSourceId==null||dataSourceId==''){");
        js.append("element.length=0;");
        js.append("return;");
        js.append("};");
        js.append("const nlTemp=element.querySelectorAll('[tk-source=\\'__temp__\\']');");
        js.append("nlTemp.forEach((c)=>element.removeChild(c));");
        js.append("const nl=element.querySelectorAll('[tk-source=\\''+dataSourceId+'\\']');");
        js.append("nl.forEach((c)=>element.removeChild(c));");

        js.append("};"); // end if

        js.append("}"); // end function
    }
}
