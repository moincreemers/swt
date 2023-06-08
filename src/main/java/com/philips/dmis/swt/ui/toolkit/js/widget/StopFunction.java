package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.TimerHandleVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class StopFunction implements JsFunction {
    public static final String ID = "stop";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widgetType == WidgetType.TIMER;
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
        dependencies.add(TimerHandleVariable.class);
        dependencies.add(EventHandlerFunction.OnStopEventHandlerFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,reason)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        //js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        //js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("const element=document.getElementById(id);");

        js.append("if(widget.%s!=null){", TimerHandleVariable.ID);
        js.append("window.clearTimeout(widget.%s);", TimerHandleVariable.ID);
        js.append("widget.%s=null;", TimerHandleVariable.ID);
        js.append("%s(id,%s);",
                JsWidgetModule.getQualifiedId(RaiseEventFunction.class),
                JsWidgetModule.getId(EventHandlerFunction.OnStopEventHandlerFunction.class));
        js.append("};");

        js.append("}"); // end function
    }
}
