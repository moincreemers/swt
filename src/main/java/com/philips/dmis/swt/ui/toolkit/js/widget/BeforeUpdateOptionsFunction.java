package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class BeforeUpdateOptionsFunction implements JsFunction {
    public static final String ID = "beforeUpdateOptions";

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
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
        parameters.add(JsParameter.getInstance("cacheType", JsType.STRING));
        parameters.add(JsParameter.getInstance("dataSourceId", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,reason,cacheType,dataSourceId)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("if(cacheType=='%s'&&implements.includes('%s')){", CacheType.ENABLED.name(), HasValue.class.getSimpleName());
        js.append("const elem=document.getElementById(id);");
        js.append("const selectedValue=%s(id);", JsWidgetModule.getId(GetFunction.class));
        js.append("elem.removeAttribute('tk-value');");
        js.append("if(widgetType=='%s'){", WidgetType.SELECT.name());
        js.append("if(elem.selectedIndex>-1){");
        js.append("elem.setAttribute('tk-value',selectedValue);");
        js.append("};");
        js.append("}else{");
        js.append("elem.setAttribute('tk-value',selectedValue);");
        js.append("};");
        js.append("%s(dataSourceId);", JsWidgetModule.getId(RemoveOptionsFunction.class));
        js.append("};"); // end if

        js.append("}"); // end function
    }
}
