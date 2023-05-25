package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class BeforeUpdateOptionsFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "beforeUpdateOptions";
    private final Widget widget;
    private final WidgetType widgetType;

    public BeforeUpdateOptionsFunction(Widget widget) {
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
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
        parameters.add(JsParameter.getInstance("cacheType", JsType.STRING));
        parameters.add(JsParameter.getInstance("dataSourceId", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(reason,cacheType,dataSourceId)=>{");

        if (widget instanceof HasValue) {
            js.append("if(cacheType=='%s'){", CacheType.ENABLED.name());
            js.append("const elem=%s();", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("const selectedValue=%s();", JsPagesModule.getId(widget, GetFunction.class));
            js.append("elem.removeAttribute('tk-value');");
            if (widgetType == WidgetType.SELECT) {
                js.append("if(elem.selectedIndex>-1){");
                js.append("elem.setAttribute('tk-value',selectedValue);");
                js.append("};");
            } else {
                js.append("elem.setAttribute('tk-value',selectedValue);");
            }
            js.append("%s(dataSourceId);", JsPagesModule.getId(widget, RemoveOptionsFunction.class));
            js.append("};");
        }

        js.append("}"); // end function
    }
}
