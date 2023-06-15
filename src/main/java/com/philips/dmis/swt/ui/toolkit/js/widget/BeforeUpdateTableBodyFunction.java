package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.ExtModuleInvoke;
import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ExtModuleEvent;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.IsObjectFunction;
import com.philips.dmis.swt.ui.toolkit.widgets.CacheType;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class BeforeUpdateTableBodyFunction implements JsFunction {
    public static final String ID = "beforeUpdateTableBody";

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
        //js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        //js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("if(cacheType=='%s'){", CacheType.ENABLED.name()); // if
        js.append("const element=document.getElementById(id);");
        js.append("element.style.display='';");
        js.append("element.textContent='';");
        js.append("const tr=document.createElement('tr');");
        js.append("const td=document.createElement('td');");
        js.append("const tempId=id+'-nodata';");
        js.append("td.id=tempId;");
        js.append("tr.append(td);");
        js.append("element.append(tr);");
        ExtModuleInvoke.renderIndirectCall(ExtModuleEvent.BEGIN_PROGRESS, null, "tempId", js);
        js.append("};"); // end if

        js.append("}"); // end function
    }
}
