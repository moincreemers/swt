package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.PageRefreshType;
import com.philips.dmis.swt.ui.toolkit.js.state.PageRefreshVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RefreshPageFunction implements JsFunction {
    public static final String ID = "refreshPage";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widgetType == WidgetType.PAGE;
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
        dependencies.add(PageRefreshVariable.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,reason)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("if(widgetType=='%s'){", WidgetType.PAGE.name()); // if
        js.append("const pr=widget.%s;", PageRefreshVariable.ID);
        js.append("if(pr=='%s'){return;}", PageRefreshType.MANUAL.getReason());
        js.append("if(reason==pr){");
        js.append("%s(id,reason);", JsWidgetModule.getId(RefreshFunction.class));
        js.append("};");
        js.append("};"); // end if

        js.append("}"); // end function
    }
}
