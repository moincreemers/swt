package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.controller.GetCurrentPageFunction;
import com.philips.dmis.swt.ui.toolkit.js.controller.JsPageControllerModule;
import com.philips.dmis.swt.ui.toolkit.js.state.EventHandlersVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RaiseEventFunction implements JsFunction {
    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return true;
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
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,eventHandlerName,event)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const currentPageId=%s();", JsPageControllerModule.getQualifiedId(GetCurrentPageFunction.class));
        js.append("const eventSourceId=(event.srcElement==undefined||event.srcElement==null)?'':event.srcElement.id;");
        js.append("const c=widget.%s[eventHandlerName.toLowerCase()];", EventHandlersVariable.ID);
        js.append("for(const i in c){"); // for
        js.append("var h=c[i];");
        js.append("if((h.pageId==''||h.pageId==currentPageId)" +
                "&&(eventSourceId==''||h.widgetId==''||h.widgetId==eventSourceId)){");
        js.append("h.fn(event);");
        js.append("};"); // end if
        js.append("};"); // end for

        js.append("}");
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("eventHandlerName", JsType.STRING));
        parameters.add(JsParameter.getInstance("event", JsType.OBJECT));
    }
}
