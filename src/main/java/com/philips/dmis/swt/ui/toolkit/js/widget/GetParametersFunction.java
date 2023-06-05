package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.ParametersVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceSupplier;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class GetParametersFunction implements JsFunction {
    public static final String ID = "getParameters";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof DataSourceSupplier;
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
        return JsType.STRING;
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const params=widget.%s;", ParametersVariable.ID);
        js.append("const payload={};");
        js.append("for(const i in params){"); // for
        js.append("var param=params[i];");
        js.append("if(param.value==null){"); // if
        js.append("payload[i]=param.defaultValue;");
        js.append("};"); // end if
        js.append("payload[i]=param.value;");
        js.append("};"); // end for
        js.append("return payload;");

        js.append("}"); // end function
    }
}
