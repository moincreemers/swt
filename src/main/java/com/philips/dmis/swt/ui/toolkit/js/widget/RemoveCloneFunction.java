package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.DataKeysVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RemoveCloneFunction implements JsFunction {
    public static final String ID = "removeClone";

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
        parameters.add(JsParameter.getInstance("templateId", JsType.STRING));
        parameters.add(JsParameter.getInstance("dataKey", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(templateId,dataKey)=>{");
        js.trace(this);

        js.append("const widget=window[templateId];");
        js.append("if(!widget.%s.hasOwnProperty(dataKey)){", DataKeysVariable.ID); // if
        js.throwError("data-key not found", "dataKey");
        js.append("};");// end if

        js.append("const slaveId=widget.%s[dataKey].slaveId;", DataKeysVariable.ID);
        js.append("const clone=window[slaveId];");
        js.append("%s(slaveId);", JsWidgetModule.getId(RemoveWidgetFunction.class));

        js.append("%s(templateId,slaveId);", JsWidgetModule.getId(RemoveSlaveFunction.class));
        js.append("%s(templateId,dataKey);", JsWidgetModule.getId(RemoveDataKeyFunction.class));
        js.append("%s(dataKey);", JsWidgetModule.getId(RemoveSubstitutionFunction.class));

        js.append("}");
    }
}
