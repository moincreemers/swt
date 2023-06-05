package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.ParentWidgetIdVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.SyncVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetIdVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class CloneFunction implements JsFunction {
    public static final String ID = "clone";

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
        parameters.add(JsParameter.getInstance("parentId", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,parentId)=>{");

        js.append("const widget=window[id];");
        js.append("const clonedWidget=structuredClone(widget);");
        js.append("clonedWidget.%s=id;", WidgetIdVariable.ID);
        js.append("clonedWidget.%s=parentId;", ParentWidgetIdVariable.ID);

        js.append("clonedWidget.%s={};", SyncVariable.ID);

        js.append("window[id]=clonedWidget;");
        js.append("return clonedWidget;");

        js.append("}");
    }
}
