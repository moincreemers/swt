package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.DataKeysVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RemoveAllClonesFunction implements JsFunction {
    public static final String ID = "removeAllClones";

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
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(templateId)=>{");
        js.trace(this);

        js.append("const widget=window[templateId];");
        js.append("const dataKeys=structuredClone(widget.%s);", DataKeysVariable.ID);
        js.append("if(dataKeys.length==0){"); // if
        js.append("return;");
        js.append("};");// end if
        js.append("for(const dataKey in dataKeys){");
        js.append("%s(templateId,dataKey);", JsWidgetModule.getId(RemoveCloneFunction.class));
        js.append("};");

        js.append("}");
    }
}
