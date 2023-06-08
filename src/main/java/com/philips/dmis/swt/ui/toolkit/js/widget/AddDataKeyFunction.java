package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.DataKeysVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class AddDataKeyFunction implements JsFunction {
    public static final String ID = "addDataKey";

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
        parameters.add(JsParameter.getInstance("slaveId", JsType.STRING));
        parameters.add(JsParameter.getInstance("dataKey", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,slaveId,dataKey)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("if(!widget.%s.hasOwnProperty(dataKey)){", DataKeysVariable.ID);
        js.append("widget.%s[dataKey]={slaveId:slaveId};", DataKeysVariable.ID);
        js.append("};");

        js.append("}"); // end function
    }
}
