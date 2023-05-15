package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class BeforeEventFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "beforeEvent";
    private final Widget widget;
    private final WidgetType widgetType;

    public BeforeEventFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return true;
    }

    @Override
    public boolean isPublic() {
        return false;
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

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("event", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(event)=>{");

        js.debug("console.log('BeforeEventFunction',event);");

        if (widgetType.isOneOf(WidgetType.MULTIPLE_CHOICE, WidgetType.SINGLE_CHOICE)) {
            js.append("if(event.type!='%s'){return;};", ChangeEventHandler.EVENT_NAME);
            js.append("const elem=%s();", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("const widgetId=elem.getAttribute('id');");
            js.append("var selectedValues=[];");
            js.append("const count=elem.childElementCount;");
            js.append("for(var i=0;i<count;i++){"); // for
            js.append("var li=document.getElementById(widgetId+'_item_'+i);");
            js.append("var inp=document.getElementById(widgetId+'_input_'+i);");
            js.append("if(inp.checked==true){"); // if
            js.append("selectedValues.push(inp.value);");
            js.append("li.classList.add('tk-selected');");
            js.append("}else{"); // else
            js.append("li.classList.remove('tk-selected');");
            js.append("};"); // end if
            js.append("};"); // end for
            js.append("elem.setAttribute('value',selectedValues.join(','));");
        }

        js.append("}"); // end function
    }
}
