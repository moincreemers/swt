package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.NotifySubscribersVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class BeforeUpdateDataProxyFunction implements JsFunction {
    public static final String ID = "beforeUpdateDataProxy";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widgetType == WidgetType.DATA_PROXY;
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

        js.append("var widget=window[id];");
        js.append("var widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("if(widgetType=='%s'&&widget.%s==true){",
                WidgetType.DATA_PROXY.name(),
                NotifySubscribersVariable.ID); // if
        js.append("%s(id,reason,cacheType);",
                JsWidgetModule.getId(BeforeUpdateSubscribersFunction.class));
        js.append("};"); // end if

        js.append("}"); // end function
    }
}
