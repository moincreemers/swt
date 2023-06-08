package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.EventContext;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.controller.GetCurrentPageFunction;
import com.philips.dmis.swt.ui.toolkit.js.controller.JsPageControllerModule;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
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
        js.append("(id,eventHandlerFunction,domEvent,slaveId,dataKey)=>{");
        js.trace(this);

        js.append("if(domEvent==undefined){domEvent=null;};");
        js.append("if(slaveId==undefined){slaveId=null;};");
        js.append("if(dataKey==undefined){dataKey=null;};");

        js.append("const currentPageId=%s();", JsPageControllerModule.getQualifiedId(GetCurrentPageFunction.class));
        // note: this is used to raise global events
        js.append("if(id==null){id=currentPageId;};");
        js.append("const widget=window[id];");
        js.append("const eventSourceId=domEvent==null||domEvent.srcElement==undefined||domEvent.srcElement==null?'':domEvent.srcElement.id;");
        js.append("const eventContext=%s;", DtoUtil.getDefault(EventContext.class, false));
        js.append("eventContext.widgetId=id;");
        js.append("eventContext.slaveId=slaveId;");
        js.append("eventContext.dataKey=dataKey;");
        js.append("eventContext.domEvent=domEvent;");

        js.append("eventHandlerFunction(eventContext);");

//        js.append("const c=widget.%s[eventHandlerName.toLowerCase()];", EventHandlersVariable.ID);
//        js.append("for(const i in c){"); // for
//        js.append("var h=c[i];");
//        js.append("if((h.pageId==''||h.pageId==currentPageId)" +
//                "&&(eventSourceId==''||h.widgetId==''||h.widgetId==eventSourceId)){");
//        js.append("h.fn(eventContext);");
//        js.append("};"); // end if
//        js.append("};"); // end for


        js.append("}");
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("eventHandlerFunction", JsType.FUNCTION));
        parameters.add(JsParameter.getInstance("domEvent", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("slaveId", JsType.STRING));
        parameters.add(JsParameter.getInstance("dataKey", JsType.OBJECT));
    }
}
