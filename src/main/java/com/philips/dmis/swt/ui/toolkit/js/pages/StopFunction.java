package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.CustomEvent;
import com.philips.dmis.swt.ui.toolkit.events.StopEvent;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class StopFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "stop";
    private final Widget widget;
    private final WidgetType widgetType;

    public StopFunction(Widget widget) {
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
        dependencies.add(EventHandlerFunction.OnStopEventHandlerFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(reason)=>{");


        js.append("if(%s!=null){",
                JsPagesModule.getId(widget, TimerHandleVariable.class));
        js.debug("console.log('timer stop');");
        js.append("window.clearTimeout(%s);",
                JsPagesModule.getId(widget, TimerHandleVariable.class));
        js.append("%s=null;",
                JsPagesModule.getId(widget, TimerHandleVariable.class));
        js.append("%s(%s);",
                EventHandlerFunction.OnStopEventHandlerFunction.ID,
                CustomEvent.valueOf(new StopEvent()));
        js.append("};");


        js.append("}"); // end function
    }
}
