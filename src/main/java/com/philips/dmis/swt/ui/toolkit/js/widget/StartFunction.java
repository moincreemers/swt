package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.TimerHandleVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.TimerIntervalVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.TimerTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.TimerType;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class StartFunction implements JsFunction {
    public static final String ID = "start";

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
        dependencies.add(StopFunction.class);
        dependencies.add(EventHandlerFunction.OnElapsedEventHandlerFunction.class);
        dependencies.add(EventHandlerFunction.OnStartEventHandlerFunction.class);
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

        js.append("%s(id,reason);", JsWidgetModule.getId(StopFunction.class));

        js.append("const callback=()=>{");
        js.append("%s(id,%s);",
                JsWidgetModule.getQualifiedId(RaiseEventFunction.class),
                JsWidgetModule.getId(EventHandlerFunction.OnElapsedEventHandlerFunction.class));
        js.append("};");

        js.append("const timerType=widget.%s;", TimerTypeVariable.ID);
        js.append("if(timerType=='%s'){", TimerType.ALARM.name()); // if
        js.append("widget.%s=window.setTimeout(callback,widget.%s);", TimerHandleVariable.ID,
                TimerIntervalVariable.ID);
        js.debug("console.log('alarm set');");
        js.append("};"); // end if

        js.append("if(timerType=='%s'){", TimerType.REPEAT.name()); // if
        js.append("widget.%s=window.setInterval(callback,widget.%s);", TimerHandleVariable.ID,
                TimerIntervalVariable.ID);
        js.debug("console.log('repeater set');");
        js.append("};"); // end if

        js.append("%s(id,%s);",
                JsWidgetModule.getQualifiedId(RaiseEventFunction.class),
                JsWidgetModule.getId(EventHandlerFunction.OnStartEventHandlerFunction.class));

        js.append("}"); // end function
    }
}
