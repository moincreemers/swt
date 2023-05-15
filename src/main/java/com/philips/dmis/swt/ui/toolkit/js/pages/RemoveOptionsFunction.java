package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HasOptions;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RemoveOptionsFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "removeOptions";
    private final Widget widget;
    private final WidgetType widgetType;

    public RemoveOptionsFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

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
        parameters.add(JsParameter.getInstance("dataSourceId", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(dataSourceId)=>{");

        if (widgetType == WidgetType.MULTIPLE_CHOICE || widgetType == WidgetType.SINGLE_CHOICE) {
            js.append("const elem=%s();", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("if(dataSourceId==undefined||dataSourceId==null||dataSourceId==''){");
            js.append("elem.textContent='';");
            js.append("return;");
            js.append("};");
            js.append("const nl=elem.querySelectorAll('[tk-source=\\''+dataSourceId+'\\']');");
            js.append("nl.forEach((c)=>elem.removeChild(c));");

        } else {
            js.append("const elem=%s();", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("if(dataSourceId==undefined||dataSourceId==null||dataSourceId==''){");
            js.append("elem.length=0;");
            js.append("return;");
            js.append("};");
            js.append("const nl=elem.querySelectorAll('[tk-source=\\''+dataSourceId+'\\']');");
            js.append("nl.forEach((c)=>elem.removeChild(c));");
        }

        js.append("}"); // end function
    }
}
