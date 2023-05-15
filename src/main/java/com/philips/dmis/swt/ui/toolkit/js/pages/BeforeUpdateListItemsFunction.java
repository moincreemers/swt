package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.ConvertHyperlinksFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.IsObjectFunction;
import com.philips.dmis.swt.ui.toolkit.widgets.CacheType;
import com.philips.dmis.swt.ui.toolkit.widgets.HtmlList;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class BeforeUpdateListItemsFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "beforeUpdateListItems";
    private final Widget widget;
    private final WidgetType widgetType;

    public BeforeUpdateListItemsFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HtmlList;
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
        dependencies.add(IsObjectFunction.class);
        dependencies.add(ConvertHyperlinksFunction.class);
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
        js.append("if(cacheType=='%s'){", CacheType.ENABLED.name());
        js.append("const element=%s();", JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("element.textContent='';");
        js.append("};");
        js.append("}"); // end function
    }
}
