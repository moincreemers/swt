package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class BeforeEventFunction implements JsFunction {
    public static final String ID = "beforeEvent";

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
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("event", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,event)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("const elem=document.getElementById(id);");

        js.ifInArray("widgetType", WidgetType.MULTIPLE_CHOICE.name(), WidgetType.SINGLE_CHOICE.name()); // if
        js.append("if(event.type!='%s'){return;};", ChangeEventHandler.EVENT_NAME);
        js.append("var selectedValues=[];");
        js.append("const count=elem.childElementCount;");
        js.append("for(var i=0;i<count;i++){"); // for
        js.append("var li=document.getElementById(id+'_item_'+i);");
        js.append("var inp=document.getElementById(id+'_input_'+i);");
        js.append("if(inp.checked==true){"); // if
        js.append("selectedValues.push(inp.value);");
        js.append("li.classList.add('tk-selected');");
        js.append("}else{"); // else
        js.append("li.classList.remove('tk-selected');");
        js.append("};"); // end if
        js.append("};"); // end for
        js.append("elem.setAttribute('value',selectedValues.join(','));");
        js.append("};"); // end if

        js.append("}"); // end function
    }
}
