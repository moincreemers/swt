package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.ParametersVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.HasURL;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetParametersFunction implements JsFunction {
    public static final String ID = "setParameters";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasURL;
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
        parameters.add(JsParameter.getInstance("obj", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,obj)=>{");
        js.trace(this);

        js.append("if(obj==undefined||obj==null||typeof obj!='object'){return;};");
        js.append("const widget=window[id];");
        js.append("const params=widget.%s;", ParametersVariable.ID);
        js.append("for(const name in obj){"); // for
        js.append("var value=obj[name];");
        js.append("if(value==null){continue;};");
        js.append("if(!params.hasOwnProperty(name)){"); // if
        js.append("params[name]={defaultValue:null,value:null};");
        js.append("};"); // end if
        js.append("params[name].value=value;", ParametersVariable.ID);
        js.append("};"); // end for

        js.append("}"); // end function
    }
}
