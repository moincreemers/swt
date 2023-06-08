package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.SlavesVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RemoveSlaveFunction implements JsFunction {
    public static final String ID = "removeSlave";

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
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,slaveId)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("if(widget.%s.includes(slaveId)){", SlavesVariable.ID);
        js.append("const indexOfSlaveId=widget.%s.indexOf(slaveId);", SlavesVariable.ID);
        js.append("widget.%s.splice(indexOfSlaveId,1);", SlavesVariable.ID);
        js.append("};");

        js.append("}"); // end function
    }
}
