package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.HasOptions;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SelectIndexFunction implements JsFunction {
    public static final String ID = "selectIndex";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasOptions;
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
        parameters.add(JsParameter.getInstance("index", JsType.NUMBER));
        parameters.add(JsParameter.getInstance("eventContext", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,index,eventContext)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        //js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("const element=document.getElementById(id);");
        js.append("var changed=false;");

        js.append("if(widgetType=='%s'){", WidgetType.SELECT.name());
        js.append("if(element.selectedIndex!=index){");
        js.append("element.selectedIndex=index;");
        js.append("changed=true;");
        js.append("};");
        js.append("};");

        js.append("if(widgetType=='%s'||widgetType=='%s'){", WidgetType.MULTIPLE_CHOICE.name(), WidgetType.SINGLE_CHOICE.name());
        js.append("const inputs=document.getElementsByName(id+'_input');");
        js.append("for(var i=0;i<inputs.length;i++){");
        js.append("var c=inputs[i];");
        js.append("var checked=(c.checked==true);");
        js.append("if(i==index&&!checked){");
        js.append("c.checked=true;");
        js.append("changed=true;");
        js.append("}else if(checked){");
        js.append("c.checked=false;");
        js.append("changed=true;");
        js.append("};");
        js.append("};");
        js.append("};");

        js.append("if(changed){");
        js.append("element.dispatchEvent(new Event('change'));");
        js.append("};");

        js.append("}"); // end function
    }
}
