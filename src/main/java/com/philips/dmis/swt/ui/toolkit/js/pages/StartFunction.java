package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.CustomEvent;
import com.philips.dmis.swt.ui.toolkit.events.ElapsedEvent;
import com.philips.dmis.swt.ui.toolkit.events.StartEvent;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.TimerType;
import com.philips.dmis.swt.ui.toolkit.widgets.Timer;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class StartFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "start";
    private final Widget widget;
    private final WidgetType widgetType;

    public StartFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

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
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(reason)=>{");

        Timer timer = (Timer) widget;
        js.debug("console.log('timer start','%s');", timer.getTimerType().name());
        js.append("%s();", JsPagesModule.getId(widget, StopFunction.class));
        js.append("const callback=()=>{");
        js.append("%s(%s);",
                EventHandlerFunction.OnElapsedEventHandlerFunction.ID,
                CustomEvent.valueOf(new ElapsedEvent()));
        js.append("};");
        if (timer.getTimerType() == TimerType.ALARM) {
            js.append("%s=window.setTimeout(callback,%d);",
                    JsPagesModule.getId(widget, TimerHandleVariable.class),
                    timer.getIntervalMilliSeconds());
            js.debug("console.log('alarm set');");
        }
        if (timer.getTimerType() == TimerType.REPEAT) {
            js.append("%s=window.setInterval(callback,%d);",
                    JsPagesModule.getId(widget, TimerHandleVariable.class),
                    timer.getIntervalMilliSeconds());
            js.debug("console.log('repeater set');");
        }
        js.append("%s(%s);",
                EventHandlerFunction.OnStartEventHandlerFunction.ID,
                CustomEvent.valueOf(new StartEvent()));


        js.append("}"); // end function
    }
}
