package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.ConvertHyperlinksFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.IsObjectFunction;
import com.philips.dmis.swt.ui.toolkit.widgets.CacheType;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class BeforeUpdateTableBodyFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "beforeUpdateTableBody";
    private final Widget widget;
    private final WidgetType widgetType;

    public BeforeUpdateTableBodyFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widgetType == WidgetType.TABLE_BODY;
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
        js.append("element.style.display='';");
        js.append("element.textContent='';");
        js.append("const tr=document.createElement('tr');");
        js.append("const td=document.createElement('td');");
        js.append("td.setAttribute('class','spinner-container');");
        js.append("const spinner=document.createElement('div');");
        js.append("spinner.setAttribute('class','spinner');");
        js.append("td.append(spinner);");
        js.append("tr.append(td);");
        js.append("element.append(tr);");
        js.append("};");
        js.append("}"); // end function
    }
}
